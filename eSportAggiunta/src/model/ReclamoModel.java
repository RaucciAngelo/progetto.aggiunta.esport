package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import beans.ReclamoBean;

public class ReclamoModel {
	private static final String TABLE_NAME="reclamo";
	static Logger log=Logger.getLogger("ReclamoModelDebugger");
	
	/**
	 * 
	 * @param reclamo
	 * @throws SQLException
	 */
	public void doSave(ReclamoBean reclamo) throws SQLException {
		log.info("ReclamoModel -> doSave");
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		log.info("doSave -> eseguo query");
		String insertSQL="insert into " + ReclamoModel.TABLE_NAME
				+ " (commento, stato, usr, ordine) "
				+ "values (?, ?, ?, ?)";

		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, reclamo.getCommento());
			preparedStatement.setString(2, reclamo.getStato());
			preparedStatement.setString(3, reclamo.getUsr());
			preparedStatement.setString(4, reclamo.getOrdine());

			preparedStatement.executeUpdate();

			connection.commit();
		} 
		finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			}	 
			finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		log.info("ReclamoModel -> doSave terminato");
	}

	public Set<ReclamoBean> doRetrieveAll(String order) throws SQLException{
		log.info("ReclamoModel -> doRetrieveAll");
		LinkedHashSet<ReclamoBean> reclami=new LinkedHashSet<ReclamoBean>();
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		log.info("doRetrieveAll -> eseguo query");
		String selectSQL="select * from " + ReclamoModel.TABLE_NAME;
		
		if (order!=null && !order.equals("")) {
			selectSQL+=" order by " + order;
		}

		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);

			ResultSet rs=preparedStatement.executeQuery();

			while (rs.next()) {
				ReclamoBean bean=new ReclamoBean();

				bean.setId(rs.getInt("id"));
				bean.setStato(rs.getString("stato"));
				bean.setUsr(rs.getString("usr"));
				bean.setOrdine(rs.getString("ordine"));
				bean.setCommento(rs.getString("commento"));
				
				reclami.add(bean);
			}
		} 
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		log.info("ReclamoModel -> doRetrieveAll terminato");
		
		return reclami;
	}
	
	public Set<ReclamoBean> doRetrieveIfAttivi() throws SQLException{
		log.info("ReclamoModel -> doRetrieveIfAttivi");
		LinkedHashSet<ReclamoBean> reclami=new LinkedHashSet<ReclamoBean>();
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		ComposizioneModel composizioneModel=new ComposizioneModel();
		
		log.info("doRetrieveIfAttivi -> eseguo query");
		String selectSQL="select * from " + ReclamoModel.TABLE_NAME + " where stato=?";

		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, ReclamoBean.IN_ATTESA);

			ResultSet rs=preparedStatement.executeQuery();

			while (rs.next()) {
				ReclamoBean bean=new ReclamoBean();

				bean.setCommento(rs.getString("commento"));
				bean.setStato(rs.getString("stato"));
				bean.setUsr(rs.getString("usr"));
				bean.setOrdine(rs.getString("ordine"));
				
				reclami.add(bean);
			}
		} 
		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		log.info("ReclamoModel -> doRetrieveIfAttivi terminato");
		
		return reclami;
	}
	
	public boolean doUpdate(ReclamoBean reclamo) throws SQLException {
		log.info("ReclamoModel -> aggiornaStato");
		int result=0;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		//Aggiunta controlli
				
		log.info("aggiornaStato -> eseguo query");
		String updateSQL="update " + ReclamoModel.TABLE_NAME + " "
					   + " set stato=? "
					   + " where id=?";

		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(updateSQL);
			
			preparedStatement.setString(1, reclamo.getStato());
			preparedStatement.setInt(2, reclamo.getId());
			
			result=preparedStatement.executeUpdate();	
			
			connection.commit();
		} 
		finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} 
			finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		log.info("ReclamoModel -> aggiornaStato terminato");	
		
		return (result!=0);
	}
	
	/**
	 * Permette di gestire i caratteri speciali nel commento di un reclamo prima di memorizzarlo
	 * @param commento
	 * @return commentoFiltrato
	 */
	public String correzione(String commento) {
		log.info("ReclamoModel -> verifico la correttezza dei caratteri nel commento del reclamo");
		if(!hasSpecialChars(commento)) {
			return commento;
		}

		log.info("ReclamoModel -> filtro commento");
		StringBuilder commentoFiltrato = new StringBuilder(commento.length());
		char c;
		for(int i=0; i<commento.length(); i++) {
			c = commento.charAt(i);
			switch(c) {
			case '<': commentoFiltrato.append("&lt;"); break;
			case '>': commentoFiltrato.append("&gt;"); break;
			case '"': commentoFiltrato.append("&quot;"); break;
			case '&': commentoFiltrato.append("&amp;"); break;
			default: commentoFiltrato.append(c);
			}
		}
		
		return commentoFiltrato.toString();
	}
	
	/**
	 * Permette di verificare che il commento di un reclamo contenga caratteri speciali
	 * @param input
	 * @return flag
	 */
	private boolean hasSpecialChars(String input) {
		boolean flag=false;
		
		if((input != null) && (input.length() > 0)) {
			char c;
			
			for(int i=0; i<input.length(); i++) {
				c = input.charAt(i);
				switch(c) {
				case '<': flag = true; break;
				case '>': flag = true; break;
				case '"': flag = true; break;
				case '&': flag = true; break;
				}
			}
		}

		return flag;
	}
	
	
}

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
	
	public void doSave(ReclamoBean reclamo) throws SQLException {
		log.info("IndirizzoModel -> doSave");
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		log.info("doSave -> verifico la correttezza dell'indirizzo da salvare");
		
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
		
		log.info("IndirizzoModel -> doSave terminato");
	}
	
	public Set<ReclamoBean> doRetrieveIfAttivi() throws SQLException{
		log.info("OrdineModel -> doRetrieveIfAttivi");
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
		log.info("OrdineModel -> doRetrieveIfAttivi terminato");
		
		return reclami;
	}
	
	public boolean doUpdate(ReclamoBean reclamo) {
		
		return false;
	}
}

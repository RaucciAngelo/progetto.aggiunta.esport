package topdown;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

import beans.ReclamoBean;


public class ReclamoModelStub {
	static Logger log=Logger.getLogger("ReclamoModelStubDebugger");
	
	public ReclamoModelStub() {
		
	}

	public void doSave(ReclamoBean reclamo) {
	
	}
	
	public Set<ReclamoBean> doRetrieveAll() {
		LinkedHashSet<ReclamoBean> reclami = new LinkedHashSet<ReclamoBean>();
		String commento="Taglia troppo piccola e prodotto macchiato.";
		
		ReclamoBean reclamoOne=new ReclamoBean();
		reclamoOne.setId(001);
		reclamoOne.setUsr("PaoloG");
		reclamoOne.setOrdine("009");
		reclamoOne.setCommento(commento);
		reclamoOne.setStato("IN_ATTESA");
		reclami.add(reclamoOne);
		
		return reclami;
	}
	
	public Set<ReclamoBean> doRetrieveIfAttivi() {
		LinkedHashSet<ReclamoBean> reclami = new LinkedHashSet<ReclamoBean>();
		
		log.info("metodo: doRetrieveIfAttivi -> metodo: doCount -> ottengo gli ordini per la generazione del numero");
		LinkedHashSet<ReclamoBean> listaOrdini=(LinkedHashSet<ReclamoBean>) doRetrieveAll();
		
		for(ReclamoBean reclamo : listaOrdini)
			if(reclamo.getStato().equals(ReclamoBean.IN_ATTESA)) 
				reclami.add(reclamo);
		
		return reclami;
	}
	
	public void doUpdate(ReclamoBean reclamo) {
		
	}
	
	public String correzione(String commento) {
		if(!hasSpecialChars(commento)) {
			return commento;
		}

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

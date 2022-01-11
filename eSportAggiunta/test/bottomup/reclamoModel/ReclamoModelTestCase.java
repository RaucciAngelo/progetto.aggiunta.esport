package bottomup.reclamoModel;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashSet;

import beans.OrdineBean;
import beans.ProdottoBean;
import beans.RecensioneBean;
import beans.ReclamoBean;
import junit.framework.TestCase;
import model.ReclamoModel;

public class ReclamoModelTestCase extends TestCase {
	public ReclamoModelTestCase(String nome) {
		super(nome);
	}
	
	@Override
	public void setUp() {
		reclamoModel=new ReclamoModel();
	}
	
	public void doSave() throws SQLException {
		//Creo prodotto
		OrdineBean ordine = new OrdineBean();
		ordine.setNumero("000002");

		//Creo il reclamo
		ReclamoBean reclamo = new ReclamoBean();
		
		//Caso corretto
		reclamo.setOrdine(ordine.getNumero());
		reclamo.setUsr("root");
		reclamo.setStato("Approvato");
		String commento="Scarpe da gioco arrivate rovinate.";
		reclamo.setCommento(commento);
		reclamo.setId(1);
		
		reclamoModel.doSave(reclamo);
				
		LinkedHashSet<ReclamoBean> reclami=(LinkedHashSet<ReclamoBean>) reclamoModel.doRetrieveAll("");
				
		//Verifico caso corretto
		assertFalse(reclami.isEmpty());
		assertNotNull(reclami);
				
		//Caso errato commento troppo corto
		reclamo.setCommento("Brutto");
		reclamo.setId(2);
				
		reclamoModel.doSave(reclamo);
				
		reclami=(LinkedHashSet<ReclamoBean>) reclamoModel.doRetrieveAll("");
				
		//Verifico caso corretto
		assertFalse(reclami.contains(reclamo));
		
	}
	
	//Test doRetrieveAll 
		public void doRetrieveAll() throws SQLException {
			assertNotNull(reclamoModel.doRetrieveAll(""));
		}
	
	//Test doRetrieveIfAttivi copiato da ordine
		public void doRetrieveIfAttivi() throws SQLException {
			//Caso corretto
			LinkedHashSet<ReclamoBean> reclami=(LinkedHashSet<ReclamoBean>) reclamoModel.doRetrieveIfAttivi();
			
			//Verifico caso corretto
			assertNotNull(reclami);
			
			for(ReclamoBean o: reclami)
				assertTrue(o.getStato().equals(ReclamoBean.IN_ATTESA));
		}
	
	
	public void doUpdate() throws SQLException {
		//Imposto delle modifiche
		
		LinkedHashSet<ReclamoBean> reclami = (LinkedHashSet<ReclamoBean>) reclamoModel.doRetrieveAll("");
		
		ReclamoBean reclamoTest = reclami.iterator().next();
		
		String stato = reclamoTest.getStato();
		String updated = "";
		if(!stato.equals(ReclamoBean.IN_ATTESA)) {
			updated = ReclamoBean.IN_ATTESA;
		} else if(!stato.equals(ReclamoBean.APPROVATO)) {
			updated = ReclamoBean.APPROVATO;
		} else if(!stato.equals(ReclamoBean.RIFIUTATO)) {
			updated = ReclamoBean.RIFIUTATO;
		} 
		reclamoTest.setStato(updated);
		reclamoModel.doUpdate(reclamoTest);
		
		reclami = (LinkedHashSet<ReclamoBean>) reclamoModel.doRetrieveAll("");
		Iterator<ReclamoBean> it = reclami.iterator();
		ReclamoBean tested = null;
		while (it.hasNext()) {
			ReclamoBean temp = it.next();
			if (reclamoTest.getId() == temp.getId()) {
				tested = temp;
				break;
			}
		}
		
		assertEquals(tested.getStato(), updated);
		tested.setStato(stato);
		reclamoModel.doUpdate(tested);
	}

	private ReclamoModel reclamoModel;
}

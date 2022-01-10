package bottomup.reclamoModel;

import java.sql.SQLException;
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
		ordine.setNumero("002");

		//Creo la taglia
		ReclamoBean reclamo = new ReclamoBean();
		
		//Caso corretto
		reclamo.setOrdine(ordine.getNumero());
		reclamo.setUsr("root");
		reclamo.setStato("Approvato");
		String commento="Scarpe da gioco arrivate rovinate.";
		reclamo.setCommento(commento);
		reclamo.setId(1);
		
		reclamoModel.doSave(reclamo);
				
		LinkedHashSet<ReclamoBean> reclami=(LinkedHashSet<ReclamoBean>) reclamoModel.doRetrieveAll(ordine.getNumero());
				
		//Verifico caso corretto
		assertFalse(reclami.isEmpty());
		assertNotNull(reclami);
				
		//Caso errato commento troppo corto
		reclamo.setCommento("Brutto");
				
		reclamoModel.doSave(reclamo);
				
		/* reclami=(LinkedHashSet<ReclamoBean>) reclamoModel.doRetrieveAll(ordine.getNumero());
				
		//Verifico caso corretto
		assertFalse(reclami.contains(reclamo)); */
		
		
	}
	
	//Test doRetrieveAll copiato da ordine
		public void doRetrieveAll() throws SQLException {
			assertNotNull(reclamoModel.doRetrieveAll(""));
		}
	
	/*public void doRetrieveAll() throws SQLException {
		//Creo prodotto
		OrdineBean ordine = new OrdineBean();
		ordine.setNumero("001");
				
		//Caso corretto
		LinkedHashSet<ReclamoBean> reclami=(LinkedHashSet<ReclamoBean>) reclamoModel.doRetrieveAll(ordine.getNumero());

		//Verifico caso corretto
		assertFalse(reclami.isEmpty());
		assertNotNull(reclami);
				
		//Caso errato
		reclami=(LinkedHashSet<ReclamoBean>) reclamoModel.doRetrieveAll("");

		//Verifico caso corretto
		assertNull(reclami);
	}*/
	
	//Test doRetrieveIfAttivi copiato da ordine
		public void doRetrieveIfAttivi() throws SQLException {
			//Caso corretto
			LinkedHashSet<ReclamoBean> reclami=(LinkedHashSet<ReclamoBean>) reclamoModel.doRetrieveIfAttivi();
			
			//Verifico caso corretto
			assertNotNull(reclami);
			
			for(ReclamoBean o: reclami)
				assertFalse(o.getStato().equals(ReclamoBean.APPROVATO));
		}
	
	/*public void doRetrieveIfAttivi() throws SQLException {
		//Creo ordine
		OrdineBean ordine = new OrdineBean();
		ordine.setNumero("001");
				
		//Caso corretto
		LinkedHashSet<ReclamoBean> reclami = (LinkedHashSet<ReclamoBean>) reclamoModel.doRetrieveIfAttivi();
		

		//Verifico caso corretto
		assertFalse(reclami.isEmpty());
		assertNotNull(reclami);
				
		//Caso errato
		reclami = (LinkedHashSet<ReclamoBean>) reclamoModel.doRetrieveIfAttivi();

		//Verifico caso corretto
		assertNull(reclami);
	}*/
	
	
	/*public void doUpdate() throws SQLException {
		//Imposto delle modifiche
		reclamoTest.setStato(ReclamoBean.IN_ATTESA);
		
		reclamoModel.doUpdate(reclamoTest);
		
		ReclamoBean reclamo = reclamoModel.doRetrieveAll(reclamoTest.getOrdine());

		assertTrue(reclamo.getStato().equals(ReclamoBean.IN_ATTESA));
	}*/

	private ReclamoModel reclamoModel;
	private ReclamoBean reclamoTest;
}

package beans;

/**
 * Questa classe modella il reclamo di un utente
 */
public class ReclamoBean {
	
	/**
	 * Costruttore di default
	 */
	public ReclamoBean() {
	}
	
	/**
	 * Permette di ottenere il valore della variabile d'istanza che modella l'id del reclamo
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Permette di modificare il valore della variabile d'istanza che modella l'id del reclamo
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Permette di ottenere il valore della variabile d'istanza che modella il commento del reclamo
	 * @return commento
	 */
	public String getCommento() {
		return commento;
	}
	
	/**
	 * Permette di modificare il valore della variabile d'istanza che modella il commento del reclamo
	 * @param commento
	 */
	public void setCommento(String commento) {
		this.commento = commento;
	}
	
	/**
	 * Permette di ottenere il valore della variabile d'istanza che modella l'username dell'utente che ha lasciato il reclamo
	 * @return usr
	 */
	public String getUsr() {
		return usr;
	}
	
	/**
	 * Permette di modificare il valore della variabile d'istanza che modella l'username dell'utente che ha lasciato il reclamo
	 * @param usr
	 */
	public void setUsr(String usr) {
		this.usr = usr;
	}
	
	/**
	 * Permette di ottenere il valore della variabile d'istanza che modella l'ordine che riguarda il reclamo
	 * @return ordine
	 */
	public String getOrdine() {
		return ordine;
	}
	
	/**
	 * Permette di modificare il valore della variabile d'istanza che modella l'ordine che riguarda il reclamo
	 * @param ordine
	 */
	public void setOrdine(String ordine) {
		this.ordine = ordine;
	}

	/**
	 * Permette di ottenere il valore della variabile d'istanza che modella lo stato del reclamo
	 * @return stato
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * Permette di modificare il valore della variabile d'istanza che modella lo stato del reclamo
	 * @param stato
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}

	/**
	 * Variabile d'istanza id
	 */
	private int id;
	
	/**
	 * Variabile d'istanza commento
	 */
	private String commento;
	
	/**
	 * Variabile d'istanza usr
	 */
	private String usr;
	
	/**
	 * Variabile d'istanza ordine
	 */
	private String ordine;
	
	/**
	 * Variabile d'istanza stato
	 */
	private String stato;
	
	/**
	 * Costanti statiche per esprimere i valori possibili per lo stato dei reclami
	 */
	public static final String IN_ATTESA="Attesa";
	public static final String APPROVATO="Approvato";
	public static final String RIFIUTATO="Rifiutato";
}

package beans;

public class ReclamoBean {
	
	public ReclamoBean() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommento() {
		return commento;
	}
	public void setCommento(String commento) {
		this.commento = commento;
	}
	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}
	public String getOrdine() {
		return ordine;
	}
	public void setOrdine(String ordine) {
		this.ordine = ordine;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	private int id;
	private String commento;
	private String usr;
	private String ordine;
	private String stato;
	
	public static final String IN_ATTESA="Attesa";
	public static final String APPROVATO="Approvato";
	public static final String RIFIUTATO="Rifiutato";
}

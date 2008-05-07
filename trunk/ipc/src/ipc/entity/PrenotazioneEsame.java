package ipc.entity;
import java.util.Date;

public class PrenotazioneEsame {
	private Long id;
	private String idStudente;
	private Long idEsame;
	private Date dataEsame;
	private Date dataPrenotazione;
	private Boolean presenzaEsame;
	private String votoEsame;
	private String esaminatore;
	private Boolean votoAccettato;
	private String nota;
	/**
	 * Attivo
	 * Disattivo
	 */
	private String status;
	
	/*Autoincrement in db*/
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id; 
	}
	
	public Long getId() {
		return this.id;
	}
	public void setIdStudente(String idStudente) {
		this.idStudente = idStudente;
	}
	
	public String getIdStudente() {
		return this.idStudente;
	}
	
	public void setIdEsame(Long idEsame) {
		this.idEsame = idEsame;
	}
	
	public Long getIdEsame() {
		return this.idEsame;
	}
	
	public void setDataEsame(Date dataEsame) {
		this.dataEsame = dataEsame;
	}
	
	public Date getDataEsame() {
		return this.dataEsame;
	}
	
	public void setDataPrenotazione(Date dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}
	
	public Date getDataPrenotazione() {
		return this.dataPrenotazione;
	}
	
	public void setPresenzaEsame(boolean presenzaEsame) {
		this.presenzaEsame = presenzaEsame;
	}
	
	public boolean getPresenzaEsame() {
		return this.presenzaEsame;
	}
	
	public void setVotoEsame(String votoEsame) {
		this.votoEsame = votoEsame;
	}
	
	public String getVotoEsame() {
		return this.votoEsame;
	}
	
	public void setEsaminatore(String esaminatore) {
		this.esaminatore = esaminatore;
	}
	
	public String getEsaminatore() {
		return this.esaminatore;
	}
	
	public void setVotoAccettato(Boolean votoAccettato) {
		this.votoAccettato = votoAccettato;
	}
	
	public Boolean getVotoAccettato() {
		return this.votoAccettato;
	}
	
	public void setNota(String nota) {
		this.nota = nota;
	}
	
	public String getNota() {
		return this.nota;
	}
	
	public void setStatus(String status) {
 		this.status = status;
 	}
 	
 	public String getStatus() {
 		return this.status;
 	}
}
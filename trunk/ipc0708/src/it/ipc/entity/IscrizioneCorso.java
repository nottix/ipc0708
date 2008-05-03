package it.ipc.entity;
import java.util.Date;

public class IscrizioneCorso {
	private Long id; // AUTOINCREMENT
	
	private String idStudente;
	private Long idCorso;
	
	private Date dataIscrizione;
	/**
	 * lo studente:
	 * - ha fatto l'esame;
	 * - si e' prenotato ad un esame
	 * - oppure nessuna delle precedenti
	 */
	private String flag;
	/**
	 * Attivo
	 * Disattivo
	 */
	private String status;
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
	
	public void setIdCorso(Long idCorso) {
		this.idCorso = idCorso;
	}
	
	public Long getIdCorso() {
		return this.idCorso;
	}
	
	public void setDataIscrizione(Date dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}
	
	public Date getDataIscrizione() {
		return this.dataIscrizione;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getFlag() {
		return this.flag;
	}
	
	public void setStatus(String status) {
 		this.status = status;
 	}
 	
 	public String getStatus() {
 		return this.status;
 	}
}
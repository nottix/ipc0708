package it.ipc.entity;
import java.util.Date;

public class Progetto {
	private Long id;
	private String titolo;
	private Date dataConsegna;
	private Integer maxUploadPerStudente;
	/**
	 * Attivo
	 * Disattivo
	 */
	private String status;
	
	/*Autoincrement in DB*/
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public String getTitolo() {
		return this.titolo;
	}
	
	public void setDataConsegna(Date dataConsegna) {
		this.dataConsegna = dataConsegna;
	}
	
	public Date getDataConsegna() {
		return this.dataConsegna;
	}
	
 	public void setMaxUploadPerStudente(Integer maxUploadPerStudente) {
		this.maxUploadPerStudente = maxUploadPerStudente;
	}
 	
 	public Integer getMaxUploadPerStudente() {
 		return this.maxUploadPerStudente;
 	}
 	
 	public void setStatus(String status) {
 		this.status = status;
 	}
 	
 	public String getStatus() {
 		return this.status;
 	}
}
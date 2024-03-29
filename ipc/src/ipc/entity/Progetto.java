package ipc.entity;
import java.util.Date;
/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class Progetto {
	private Long id;
	private String titolo;
	private Date dataConsegna;
	private Integer maxUploadPerStudente;
	private Integer maxDimGruppo;
	private Long idCorso;
	/**
	 * Attivo
	 * Disattivo
	 */
	private String status;
	
	public void setIdCorso(Long id) {
		this.idCorso=id;
	}
	
	public Long getIdCorso() {
		return this.idCorso;
	}
	
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
 	
 	public void setMaxDimGruppo(Integer maxDimGruppo) {
		this.maxDimGruppo = maxDimGruppo;
	}
 	
 	public Integer getMaxDimGruppo() {
 		return this.maxDimGruppo;
 	}
 	
 	public void setStatus(String status) {
 		this.status = status;
 	}
 	
 	public String getStatus() {
 		return this.status;
 	}
}
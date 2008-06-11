package ipc.entity;
import java.util.Date;
/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class Esame {
	private Long id;
	private Long idCorso;
	private Date dataInizioPeriodoPrenotazione;
	private Date dataFinePeriodoPrenotazione;
	private Date dataEsame;
	// Informazioni aggiuntive
	private String auleEsame;
	/**
	 * Attivo
	 * Disattivo
	 */
	private String status;
	
	/*Autoincrement by database*/
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}
	
	public void setDataEsame(Date dataEsame) {
		this.dataEsame = dataEsame;
	}
	
	public Date getDataEsame() {
		return this.dataEsame;
	}
	
	public void setAuleEsame(String auleEsame) {
		this.auleEsame = auleEsame;
	}
	
	public String getAuleEsame() {
		return this.auleEsame;
	}

	public Long getId() {
		return this.id;
	}
	
	public Long getIdCorso() {
		return this.idCorso;
	}
	
	public void setIdCorso(Long idCorso) {
		this.idCorso = idCorso;
	}
	
	public void setDataInizioPeriodoPrenotazione(Date dataInizioPeriodoPrenotazione) {
		this.dataInizioPeriodoPrenotazione = dataInizioPeriodoPrenotazione;
	}
	
	public Date getDataInizioPeriodoPrenotazione() {
		return this.dataInizioPeriodoPrenotazione;
	}
	
	public void setDataFinePeriodoPrenotazione(Date dataFinePeriodoPrenotazione) {
		this.dataFinePeriodoPrenotazione = dataFinePeriodoPrenotazione;
	}
	
	public Date getDataFinePeriodoPrenotazione() {
		return this.dataFinePeriodoPrenotazione;
	}
	
	public void setStatus(String status) {
 		this.status = status;
 	}
 	
 	public String getStatus() {
 		return this.status;
 	}
 	
 	public Boolean isDisponibile() {
 		if( this.getDataInizioPeriodoPrenotazione().before(new Date()) && this.getDataFinePeriodoPrenotazione().after(new Date()) ) {
 			return true;
 		}
 		return false;
 	}
}
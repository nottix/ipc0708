package it.ipc.entity;
import java.util.Date;

public class Esame {
	private Long id;
	private Date dataInizioPeriodoPrenotazione;
	private Date dataFinePeriodoPrenotazione;
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

	public Long getId() {
		return this.id;
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
}
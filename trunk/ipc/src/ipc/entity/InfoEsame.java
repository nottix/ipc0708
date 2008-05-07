package ipc.entity;

import java.util.Date;

public class InfoEsame {
	private Long id;
	private Long idEsame;
	private Date dataEsame;
	// Informazioni aggiuntive
	private String aulaEsame;
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
	
	public void setAulaEsame(String aulaEsame) {
		this.aulaEsame = aulaEsame;
	}
	
	public String getAulaEsame() {
		return this.aulaEsame;
	}
	
	public void setStatus(String status) {
 		this.status = status;
 	}
 	
 	public String getStatus() {
 		return this.status;
 	}
}

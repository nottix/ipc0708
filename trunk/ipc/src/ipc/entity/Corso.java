package ipc.entity;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;

public class Corso {
	private Long id;
	private String nome;
	private String acronimo;
	private String descrizione;
	private String titolare1;
	private String titolare2; // OPZIONALE
	private String comunicazioni;
	private Date dataApertura;
	private Date dataChiusura;
	private Set elencoCollaboratori = new HashSet();
	private Set corsiPropedeutici = new HashSet(); // OPZIONALE
	/**
	 * Attivo
	 * Disattivo
	 */
	private String status;
	
	/*Autoincrement througth database*/
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}
	
	public String getAcronimo() {
		return this.acronimo;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getDescrizione() {
		return this.descrizione;
	}
	
	public void setTitolare1(String titolare1) {
		this.titolare1 = titolare1;
	}
	
	public String getTitolare1() {
		return this.titolare1;
	}
	
	public void setTitolare2(String titolare2) {
		this.titolare2 = titolare2;
	}
	
	public String getTitolare2() {
		return this.titolare2;
	}
	
	public void setComunicazioni(String comunicazioni) {
		this.comunicazioni = comunicazioni;
	}
	
	public String getComunicazioni() {
		return this.comunicazioni;
	}
	
	public void setDataApertura(Date dataApertura) {
		this.dataApertura = dataApertura;
	}
	
	public Date getDataApertura() {
		return this.dataApertura;
	}
	
	public void setDataChiusura(Date dataChiusura) {
		this.dataChiusura = dataChiusura;
	}
	
	public Date getDataChiusura() {
		return this.dataChiusura;
	}
	
	public void setElencoCollaboratori(Set elencoCollaboratori) {
		this.elencoCollaboratori = elencoCollaboratori;
	}
	
	public Set getElencoCollaboratori() {
		return this.elencoCollaboratori;
	}
	
	public void setCorsiPropedeutici(Set corsiPropedeutici) {
		this.corsiPropedeutici = corsiPropedeutici;
	}
	
	public Set getCorsiPropedeutici() {
		return this.corsiPropedeutici;
	}
	
	public void setStatus(String status) {
 		this.status = status;
 	}
 	
 	public String getStatus() {
 		return this.status;
 	}
}
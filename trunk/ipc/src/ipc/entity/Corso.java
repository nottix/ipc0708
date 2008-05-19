package ipc.entity;

import java.util.*;
import ipc.entity.*;

public class Corso {
	private Long id;
	private String nome;
	private String acronimo;
	private String descrizione;
	private String comunicazioni;
	private Date dataApertura;
	private Date dataChiusura;
	private Set elencoCollaboratori = new HashSet();
	private Set elencoTitolari = new HashSet();
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
	
	public Set getElencoTitolari() {
		return this.elencoTitolari;
	}
	
	public void setElencoTitolari(Set elencoTitolari) {
		this.elencoTitolari = elencoTitolari;
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
 	
 	public Boolean isDisponibile() {
 		if( this.getDataApertura().before(new Date()) && this.getDataChiusura().after(new Date()) ) {
 			return true;
 		}
 		return false;
 	}
 	
 	public Boolean isTitolare(String email) {
 		Iterator<Account> i = this.elencoTitolari.iterator();
 		Account account;
 		while(i.hasNext()) {
 			System.out.println("isTitolare");
 			account = i.next();
 			if(account.getEmail().equals(email)) 
 				return true;
 		}
 		return false;
 	}
 	
 	public Boolean isCollaboratore(String email) {
 		Iterator<Account> i = this.elencoCollaboratori.iterator();
 		Account account;
 		while(i.hasNext()) {
 			System.out.println("isColl");
 			account = i.next();
 			if(account.getEmail().equals(email)) 
 				return true;
 		}
 		return false;
 	}
 	
}
package it.ipc.entity;

import java.util.HashSet;
import java.util.Set;

public class Gruppo {
	private Long id; // AUTOINCREMENT
	private Integer maxStudenti;
	private Integer minStudenti; // da controllare
	private Set listaStudenti = new HashSet();
	/**
	 * Attivo
	 * Disattivo
	 */
	private String status;
	
	/*Autoincrement*/
	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setMaxStudenti(Integer maxStudenti) {
		this.maxStudenti = maxStudenti;
	}
	
	public Integer getMaxStudenti() {
		return this.maxStudenti;
	}
	
	public void setMinStudenti(Integer minStudenti) {
		this.minStudenti = minStudenti;
	}
	
	public Integer getMinStudenti() {
		return this.minStudenti;
	}
	
	public void setListaStudenti(Set listaStudenti) {
		this.listaStudenti = listaStudenti;
	}
	
	public Set getListaStudenti() {
		return this.listaStudenti;
	}
	
	public void setStatus(String status) {
 		this.status = status;
 	}
 	
 	public String getStatus() {
 		return this.status;
 	}
}
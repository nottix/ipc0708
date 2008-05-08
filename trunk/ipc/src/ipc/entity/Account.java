package ipc.entity;

public class Account {
	private String nome;
	private String cognome;
	private String email;
	private String password;
	/**
	 * Attivo
	 * Disattivo
	 * In attesa di conferma
	 */
	private String status;
	/**
	 * Studente
	 * Professore
	 * Gestore di Sistema
	 */
	private String tipologia;
	private Boolean isDirettore; // Solo per account Professore
	private String matricola; // Solo per account Studente
	private Boolean isTitolare; // Solo per account Professore
	private Boolean canUpload; // pu� o non pu� uploadare dei file
	private String noteUpload; // OPZIONALE solo per l'account Studente
	private String noteStud; // OPZIONALE
	private String noteProf; // OPZIONALE
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getCognome() {
		return this.cognome;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	
	public String getTipologia() {
		return this.tipologia;
	}
	
	public void setIsDirettore(Boolean isDirettore) {
		this.isDirettore = isDirettore;
	}
	
	public Boolean getIsDirettore() {
		return this.isDirettore;
	}
	
	public void setIsTitolare(Boolean isTitolare) {
		this.isTitolare = isTitolare;
	}
	
	public Boolean getIsTitolare() {
		return this.isTitolare;
	}
	
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	
	public String getMatricola() {
		return this.matricola;
	}
	
	public void setCanUpload(Boolean canUpload) {
		this.canUpload = canUpload;
	}
	
	public Boolean getCanUpload() {
		return this.canUpload;
	}
	
	public void setNoteUpload(String noteUpload) {
		this.noteUpload = noteUpload;
	}
	
	public String getNoteUpload() {
		return this.noteUpload;
	}
	
	public void setNoteStud(String noteStud) {
		this.noteStud = noteStud;
	}
	
	public String getNoteStud() {
		return this.noteStud;
	}
	
	public void setNoteProf(String noteProf) {
		this.noteProf = noteProf;
	}
	
	public String getNoteProf() {
		return this.noteProf;
	}
}
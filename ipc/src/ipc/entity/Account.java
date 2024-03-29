package ipc.entity;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class Account {
	private String nome;
	private String cognome;
	private String email;
	private String password;
	/**
	 * Attivo
	 * Disattivo
	 * In attesa di conferma
	 * Richiesta ripristino password
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
	private Boolean isGestore; // Solo per account Professore
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
	
	public void setIsGestore(Boolean isGestore) {
		this.isGestore = isGestore;
	}
	
	public Boolean getIsGestore() {
		return this.isGestore;
	}
	
	/**
	 * TODO: non sarebbe meglio mettere un controllo se la matricola non e' un
	 * numero?
	 */
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
	
	public Boolean login(String email, String password) {
		if(email.equals(this.email) && convertToMD5(password).equals(this.password)) {
			System.out.println("Logged");
			return true;
		}
		return false;
	}
	
	public static String convertToMD5(String p) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch(NoSuchAlgorithmException nsae) {
			System.exit(1);
		}
		md.update(p.getBytes());
		return (new BigInteger(1,md.digest())).toString(16);
	}
	
	public static String generatePassword() {
		String alfabeto = "0987654321poiuytrewqlhgfdsamvcxzPOIUYTREWQLKJHGFDSAMBVCXZ";
		String str = "";
		Random r = new Random();
		for (int i=0; i < 8; i++)
			str += alfabeto.toCharArray()[r.nextInt(alfabeto.length())];
		return str;
	}
}

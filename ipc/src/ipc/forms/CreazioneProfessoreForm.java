package ipc.forms;

import ipc.utils.Utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 7 fields on this form:
 * <ul>
 * <li>nome - [your comment here]
 * <li>cognome - [your comment here]
 * <li>email - [your comment here]
 * <li>password - [your comment here]
 * <li>confPassword - [your comment here]
 * <li>isDirettore - [your comment here]
 * <li>isGestore - [your comment here]
 * </ul>
 *
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class CreazioneProfessoreForm extends ActionForm {

	private static final long serialVersionUID = 8L;
	
	private String nome = null;
    private String cognome = null;
    private String email = null;
    private String password = null;
    private String confPassword = null;
    private String isDirettore = null;
    private String isGestore = null;

    /**
     * Get nome
     * @return String
     */
    public String getNome() {
    	return nome;
    }

    /**
     * Set nome
     * @param <code>String</code>
     */
    public void setNome(String n) {
    	this.nome = n;
    }

    /**
     * Get cognome
     * @return String
     */
    public String getCognome() {
    	return cognome;
    }

    /**
     * Set cognome
     * @param <code>String</code>
     */
    public void setCognome(String c) {
    	this.cognome = c;
    }

    /**
     * Get email
     * @return String
     */
    public String getEmail() {
    	return email;
    }

    /**
     * Set email
     * @param <code>String</code>
     */
    public void setEmail(String e) {
    	this.email = e;
    }

    /**
     * Get password
     * @return String
     */
    public String getPassword() {
    	return password;
    }

    /**
     * Set password
     * @param <code>String</code>
     */
    public void setPassword(String p) {
    	this.password = p;
    }

    /**
     * Get confPassword
     * @return String
     */
    public String getConfPassword() {
    	return confPassword;
    }

    /**
     * Set confPassword
     * @param <code>String</code>
     */
    public void setConfPassword(String c) {
    	this.confPassword = c;
    }

    /**
     * Get isDirettore
     * @return String
     */
    public String getIsDirettore() {
    	return isDirettore;
    }

    /**
     * Set isDirettore
     * @param <code>String</code>
     */
    public void setIsDirettore(String i) {
    	this.isDirettore = i;
    }

    /**
     * Get isGestore
     * @return String
     */
    public String getIsGestore() {
    	return isGestore;
    }

    /**
     * Set isGestore
     * @param <code>String</code>
     */
    public void setIsGestore(String i) {
    	this.isGestore = i;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
		nome = null;
		cognome = null;
		email = null;
		password = null;
		confPassword = null;
		isDirettore = null;
		isGestore = null;	
    }

    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		boolean check_pass = true;
		if((nome != null) && (nome.length() == 0))
			errors.add("nome", new ActionError("nome.error"));
		if((cognome != null) && (cognome.length() == 0))
			errors.add("cognome", new ActionError("cognome.error"));
		if((email != null) && (email.length() == 0))
			errors.add("email", new ActionError("email.error"));
		else if(!Utils.check_email(email))
			errors.add("email", new ActionError("email.malformed"));
	    System.out.println("pwd: "+password+", cpwd: "+confPassword);
		if((password != null) && (password.length() == 0)) {
			check_pass = false;
			errors.add("password", new ActionError("password.error"));
		}
		if((confPassword != null) && (confPassword.length() == 0)) {
			check_pass = false;
			errors.add("password", new ActionError("passwordcheck.error"));
		}
		if((check_pass == true) && (!password.equals(confPassword))) {
			System.out.println("Error");
			errors.add("password", new org.apache.struts.action.ActionError("passwordmatch.error"));
		}
		return errors;
    }
}

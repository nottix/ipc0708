package ipc.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 5 fields on this form:
 * <ul>
 * <li>nome - [your comment here]
 * <li>cognome - [your comment here]
 * <li>matricola - [your comment here]
 * <li>note - [your comment here]
 * <li>email - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class VisualizzaAccountForm extends ActionForm {
	
	private static final long serialVersionUID = 6L;

	private String nome = null;

    private String cognome = null;

    private String matricola = null;

    private String note = null;

    private String email = null;
    
    private String isDirettore = null;
    
    private String isGestore = null;

    public String getIsDirettore() {
    	return this.isDirettore;
    }
    
    public String getIsGestore() {
    	return this.isGestore;
    }
    
    public void setIsDirettore(String d) {
    	this.isDirettore = d;
    }
    
    public void setIsGestore(String g) {
    	this.isGestore = g;
    }
    
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
     * Get matricola
     * @return String
     */
    public String getMatricola() {
    	return matricola;
    }

    /**
     * Set matricola
     * @param <code>String</code>
     */
    public void setMatricola(String m) {
    	this.matricola = m;
    }

    /**
     * Get note
     * @return String
     */
    public String getNote() {
    	return note;
    }

    /**
     * Set note
     * @param <code>String</code>
     */
    public void setNote(String n) {
    	this.note = n;
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

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	// Reset values are provided as samples only. Change as appropriate.
    	nome = null;
    	cognome = null;
    	matricola = null;
    	note = null;
    	email = null;
    }

    public ActionErrors validate(ActionMapping mapping,
    							 HttpServletRequest request) {

    	ActionErrors errors = new ActionErrors();
    	if((nome == null) || (nome.length() == 0))
    		errors.add("nome", new ActionError("nome.error"));
    	if((cognome == null) || (cognome.length() == 0))
    		errors.add("cognome", new ActionError("cognome.error"));
    	if((matricola == null) || (matricola.length() == 0))
    		errors.add("matricola", new ActionError("matricola.error"));
    	if((email == null) || (email.length() == 0))
    		errors.add("email", new ActionError("email.error"));
    	else if(!RichiestaRegStudenteForm.check_email(email))
    		errors.add("email", new ActionError("email.malformed"));
    	return errors;

    }
}

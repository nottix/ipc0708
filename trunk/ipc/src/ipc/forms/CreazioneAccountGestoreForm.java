package ipc.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 5 fields on this form:
 * <ul>
 * <li>nome - [your comment here]
 * <li>cognome - [your comment here]
 * <li>email - [your comment here]
 * <li>password - [your comment here]
 * <li>confPassword - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class CreazioneAccountGestoreForm extends ActionForm

{

    private String nome = null;

    private String cognome = null;

    private String email = null;

    private String password = null;

    private String confPassword = null;

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

    public void reset(ActionMapping mapping, HttpServletRequest request) {

	// Reset values are provided as samples only. Change as appropriate.

	nome = null;
	cognome = null;
	email = null;
	password = null;
	confPassword = null;

    }

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {

	ActionErrors errors = new ActionErrors();
	// Validate the fields in your form, adding
	// adding each error to this.errors as found, e.g.

	// if ((field == null) || (field.length() == 0)) {
	//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
	// }
	return errors;

    }
}

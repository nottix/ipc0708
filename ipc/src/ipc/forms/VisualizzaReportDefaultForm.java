package ipc.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 4 fields on this form:
 * <ul>
 * <li>matricolaCheck - [your comment here]
 * <li>emailCheck - [your comment here]
 * <li>nomeCheck - [your comment here]
 * <li>cognomeCheck - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class VisualizzaReportDefaultForm extends ActionForm {
	private static final long serialVersionUID = 36L;

	private String matricola = null;

    private String email = null;

    private String nome = null;

    private String cognome = null;

    /**
     * Get matricolaCheck
     * @return String
     */
    public String getMatricola() {
    	return matricola;
    }

    /**
     * Set matricolaCheck
     * @param <code>String</code>
     */
    public void setMatricola(String m) {
    	this.matricola = m;
    }

    /**
     * Get emailCheck
     * @return String
     */
    public String getEmail() {
    	return email;
    }

    /**
     * Set emailCheck
     * @param <code>String</code>
     */
    public void setEmail(String e) {
    	this.email = e;
    }

    /**
     * Get nomeCheck
     * @return String
     */
    public String getNome() {
    	return nome;
    }

    /**
     * Set nomeCheck
     * @param <code>String</code>
     */
    public void setNome(String n) {
    	this.nome = n;
    }

    /**
     * Get cognomeCheck
     * @return String
     */
    public String getCognome() {
    	return cognome;
    }

    /**
     * Set cognomeCheck
     * @param <code>String</code>
     */
    public void setCognome(String c) {
    	this.cognome = c;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
		matricola = null;
		email = null;
		nome = null;
		cognome = null;
    }

    public ActionErrors validate(ActionMapping mapping,
    							 HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
	
		return errors;
    }
}

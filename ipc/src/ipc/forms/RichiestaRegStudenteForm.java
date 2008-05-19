package ipc.forms;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 6 fields on this form:
 * <ul>
 * <li>nome - [your comment here]
 * <li>cognome - [your comment here]
 * <li>matricola - [your comment here]
 * <li>password - [your comment here]
 * <li>passwordCheck - [your comment here]
 * <li>email - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class RichiestaRegStudenteForm extends ActionForm {

	private static final long serialVersionUID = 2L;

	private String nome = null;

    private String cognome = null;

    private String matricola = null;

    private String password = null;

    private String passwordCheck = null;

    private String email = null;

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
     * Get passwordCheck
     * @return String
     */
    public String getPasswordCheck() {
	return passwordCheck;
    }

    /**
     * Set passwordCheck
     * @param <code>String</code>
     */
    public void setPasswordCheck(String p) {
	this.passwordCheck = p;
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
	password = null;
	passwordCheck = null;
	email = null;

    }

    public ActionErrors validate(ActionMapping mapping,
			 HttpServletRequest request) {
    	ActionErrors errors = new ActionErrors();
		boolean pwdchk = true;
		if((nome == null) || (nome.length() == 0))
			errors.add("nome", new ActionError("nome.error"));
		if((cognome == null) || (cognome.length() == 0))
			errors.add("cognome", new ActionError("cognome.error"));
		if((matricola == null) || (matricola.length() == 0))
			errors.add("matricola", new ActionError("matricola.error"));
		if((password == null) || (password.length() == 0)) {
			pwdchk = false;
			errors.add("password", new ActionError("password.error"));
		}
		if((passwordCheck == null) || (passwordCheck.length() == 0)) {
			pwdchk = false;
			errors.add("passwordCheck", new ActionError("passwordcheck.error"));
		}
		if(pwdchk && !password.equals(passwordCheck))
			errors.add("password", new ActionError("passwordmatch.error"));
		if((email == null) || (email.length() == 0))
			errors.add("email", new ActionError("email.error"));
		else if(check_email(email) == false)
			errors.add("email", new ActionError("email.malformed"));
		return errors;
    }

    /*If it returns false we have an invalid email!*/
    public static boolean check_email(String email) {
		boolean test1 = Pattern.compile("[a-zA-Z0-9_]+[.[a-zA-Z0-9]+]*@[a-zA-Z0-9_]+[.[a-zA-Z]+]+").matcher(email).matches();
		boolean test2 = Pattern.compile("^\\S+@\\S+$").matcher(email).matches();
		//boolean test3 = Pattern.compile(".+@.+\\.+").matcher(email).matches();
		boolean test4 = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(email).matches();
		return test1 && test2 && /*test3 &&*/ test4;
    }
}

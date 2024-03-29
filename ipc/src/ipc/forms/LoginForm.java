package ipc.forms;

import ipc.utils.Utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 2 fields on this form:
 * <ul>
 * <li>email - [your comment here]
 * <li>password - [your comment here]
 * </ul>
 *
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class LoginForm extends ActionForm {
	private static final long serialVersionUID = 17L;

	private String email = null;
    private String password = null;
    private String message = null;

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

    public String getMessage() {
    	return message;
    }
    
    public void setMessage(String m) {
    	this.message = m;
    }
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	email = null;
    	password = null;
    }

    public ActionErrors validate(ActionMapping mapping,
    							 HttpServletRequest request) {
    	ActionErrors errors = new ActionErrors();
    	if((this.email == null) || (this.email.length() == 0)) {
    		errors.add("email", new ActionError("email.error"));
    	} else if(!Utils.check_email(email)) {
    		errors.add("email", new ActionError("email.malformed"));
    	}
    	if((this.password == null) || (this.password.length() == 0)) {
    		errors.add("password", new ActionError("password.error"));
    	}
    	return errors;
    }
}

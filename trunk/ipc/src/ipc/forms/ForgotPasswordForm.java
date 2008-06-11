package ipc.forms;

import ipc.utils.Utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 1 field on this form:
 * <ul>
 * <li>email - [your comment here]
 * </ul>
 *
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class ForgotPasswordForm extends ActionForm {
	private static final long serialVersionUID = 10L;
	
	private String email = null;

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
    	email = null;
    }

    public ActionErrors validate(ActionMapping mapping,
    							 HttpServletRequest request) {
    	ActionErrors errors = new ActionErrors();

    	if((this.email != null) && (this.email.length() == 0))
    		errors.add("email", new ActionError("email.error"));
    	else if(!Utils.check_email(email))
    		errors.add("email", new ActionError("email.malformed"));
    	return errors;
    }
}

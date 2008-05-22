package ipc.forms;

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
 * <li>dataIscrizione - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class ConfermaIscrizioneCorsoForm extends ActionForm

{
	private static final long serialVersionUID = 7L;

	private String submit = null;
    private String email = null;
    private String dataIscrizione = null;

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
    
    public String getSubmit() {
    	return submit;
    }

    public void setSubmit(String e) {
    	this.submit = e;
    }

    /**
     * Get dataIscrizione
     * @return String
     */
    public String getDataIscrizione() {
    	return dataIscrizione;
    }

    /**
     * Set dataIscrizione
     * @param <code>String</code>
     */
    public void setDataIscrizione(String d) {
    	this.dataIscrizione = d;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	email = null;
    	dataIscrizione = null;
    }

    public ActionErrors validate(ActionMapping mapping,
    							 HttpServletRequest request) {
    	ActionErrors errors = new ActionErrors();
//    	if((dataIscrizione == null) || (dataIscrizione.length() == 0))
//    		errors.add("dataIscrizione", new ActionError("data.iscrizione.error"));
//		if((email == null) || (email.length() == 0))
//			errors.add("email", new ActionError("email.error"));
//		else if(!RichiestaRegStudenteForm.check_email(email))
//			errors.add("email", new ActionError("email.malformed"));
		return errors;
    }
}

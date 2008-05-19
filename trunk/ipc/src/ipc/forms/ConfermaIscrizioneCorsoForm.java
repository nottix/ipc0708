package ipc.forms;

import javax.servlet.http.HttpServletRequest;
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

	// Reset values are provided as samples only. Change as appropriate.

	email = null;
	dataIscrizione = null;

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

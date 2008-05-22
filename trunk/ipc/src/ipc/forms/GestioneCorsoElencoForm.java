package ipc.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 1 field on this form:
 * <ul>
 * <li>radio - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class GestioneCorsoElencoForm extends ActionForm

{

    /**
	 * 
	 */
	private static final long serialVersionUID = 14L;
	private String radio = null;

    /**
     * Get radio
     * @return String
     */
    public String getRadio() {
	return radio;
    }

    /**
     * Set radio
     * @param <code>String</code>
     */
    public void setRadio(String r) {
	this.radio = r;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {

	// Reset values are provided as samples only. Change as appropriate.

	radio = null;

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

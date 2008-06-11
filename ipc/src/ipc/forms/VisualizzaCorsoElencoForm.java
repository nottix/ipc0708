package ipc.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 2 fields on this form:
 * <ul>
 * <li>radio - [your comment here]
 * <li>submit - [your comment here]
 * </ul>
 *
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class VisualizzaCorsoElencoForm extends ActionForm {
	private static final long serialVersionUID = 28L;

	private String radio = null;

    private String submit = null;

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

    /**
     * Get submit
     * @return String
     */
    public String getSubmit() {
	return submit;
    }

    /**
     * Set submit
     * @param <code>String</code>
     */
    public void setSubmit(String s) {
	this.submit = s;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	radio = null;
		submit = null;
    }

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {
    	ActionErrors errors = new ActionErrors();
		return errors;
    }
}

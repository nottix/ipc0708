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
public class IscrizioneCorsoElencoForm extends ActionForm {
	private static final long serialVersionUID = 28L;

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
		radio = null;
	}

	public ActionErrors validate(ActionMapping mapping,
								 HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		return errors;
	}
}

package ipc.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * @version 	1.0
 * @author
 */
public class ConfermaIscrizioneForm extends ActionForm {

	private static final long serialVersionUID = 8L;

	public void reset(ActionMapping mapping, HttpServletRequest request) {

	// Reset field values here.

    }

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {

	ActionErrors errors = new ActionErrors();

	return errors;

    }
}

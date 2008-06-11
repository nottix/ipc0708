package ipc.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class AttivazioneAccountStudenteForm extends ActionForm {

	private static final long serialVersionUID = 1L;

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    }

    public ActionErrors validate(ActionMapping mapping,
    							 HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		return errors;
    }
}

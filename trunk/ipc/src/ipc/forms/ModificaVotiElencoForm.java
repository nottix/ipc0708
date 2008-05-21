package ipc.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 2 fields on this form:
 * <ul>
 * <li>votoAccettato - [your comment here]
 * <li>radio - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class ModificaVotiElencoForm extends ActionForm {
	private static final long serialVersionUID = 23L;

	private String votoAccettato = null;
    private String radio = null;

    /**
     * Get votoAccettato
     * @return String
     */
    public String getVotoAccettato() {
    	return votoAccettato;
    }

    /**
     * Set votoAccettato
     * @param <code>String</code>
     */
    public void setVotoAccettato(String v) {
    	this.votoAccettato = v;
    }

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
		votoAccettato = null;
		radio = null;
    }

    public ActionErrors validate(ActionMapping mapping,
    							 HttpServletRequest request) {

    	ActionErrors errors = new ActionErrors();

    	return errors;
    }
}

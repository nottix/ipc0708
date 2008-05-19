package ipc.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 1 field on this form:
 * <ul>
 * <li>acronimo - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class GestioneCorsoForm extends ActionForm {
	private static final long serialVersionUID = 15L;
	
	private String acronimo = null;

    /**
     * Get acronimo
     * @return String
     */
    public String getAcronimo() {
    	return acronimo;
    }

    /**
     * Set acronimo
     * @param <code>String</code>
     */
    public void setAcronimo(String a) {
    	this.acronimo = a;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
		acronimo = null;
    }

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {

    	ActionErrors errors = new ActionErrors();
    	if((acronimo == null) || (acronimo.length() == 0))
    		errors.add("acronimo", new ActionError("acronimo.error"));
    	return errors;

    }
}

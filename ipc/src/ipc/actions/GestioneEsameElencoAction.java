package ipc.actions;

import ipc.entity.Corso;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ipc.control.*;

/**
 * @version 	1.0
 * @author
 */
public class GestioneEsameElencoAction extends Action

{
	private List<Corso> elencoCorsi = null;
	
	public List<Corso> getElencoCorsi() {
		return this.elencoCorsi;
	}

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        GestioneEsameController control = new GestioneEsameController();
        
        try {

        	elencoCorsi = control.getElencoCorsi();
            request.setAttribute("elencoCorsi", elencoCorsi);

        } catch (Exception e) {

            // Report the error using the appropriate name and ID.
            errors.add("name", new ActionError("id"));

        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.

        if (!errors.isEmpty()) {
            saveErrors(request, errors);

            // Forward control to the appropriate 'failure' URI (change name as desired)
            forward = mapping.findForward("error");

        } else {

            // Forward control to the appropriate 'success' URI (change name as desired)
            forward = mapping.findForward("init");

        }

        // Finish with
        return (forward);

    }
}

package ipc.actions;

import java.util.List;

import ipc.control.GestioneAccountController;
import ipc.entity.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author
 */
public class RipristinaPasswordAccountAction extends Action

{

	private List<Account> elencoAccount;
	
	public List<Account> getElencoAccount() {
		return this.elencoAccount;
	}
	
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        GestioneAccountController gestioneAccountController = new GestioneAccountController();

        try {

        	elencoAccount = gestioneAccountController.getElencoAccount();
        	request.setAttribute("elencoAccount", elencoAccount);

        } catch (Exception e) {

            // Report the error using the appropriate name and ID.
            errors.add("name", new ActionError("id"));

        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.

        if (!errors.isEmpty()) {
            saveErrors(request, errors);

            // Forward control to the appropriate 'failure' URI (change name as desired)
            //	forward = mapping.findForward(non riuscito");

        } else {

            // Forward control to the appropriate 'success' URI (change name as desired)
            forward = mapping.findForward("init");

        }

        // Finish with
        return (forward);

    }
}

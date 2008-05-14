package ipc.actions;

import java.util.Hashtable;

import ipc.entity.Account;
import ipc.forms.ModificaAccountStudenteForm;
import ipc.control.*;
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
public class ModAccStudAction extends Action

{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        GestioneAccountController gestioneAccountController = new GestioneAccountController();
        ModificaAccountStudenteForm modAcc = (ModificaAccountStudenteForm)form;
        
        try {

        	Hashtable hash = new Hashtable<String, Object>();
        	hash.put("nome", modAcc.getNome());
        	hash.put("cognome", modAcc.getCognome());
        	hash.put("matricola", modAcc.getMatricola());
        	hash.put("noteStud", modAcc.getNote());
    		if(!gestioneAccountController.modificaAccountStudente(modAcc.getEmail(), hash))
    			errors.add("name", new ActionError("id"));;

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
            forward = mapping.findForward("success");

        }

        // Finish with
        return (forward);

    }
}

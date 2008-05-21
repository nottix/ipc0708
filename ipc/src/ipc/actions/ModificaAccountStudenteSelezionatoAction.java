package ipc.actions;

import ipc.entity.Account;
import ipc.control.GestioneAccountController;
import ipc.forms.ModificaAccountStudenteForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ModificaAccountStudenteSelezionatoAction extends Action {

	private Account account;
	
	public Account getAccount() {
		return account;
	}
	
    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        GestioneAccountController gestioneAccountController = new GestioneAccountController();
        ModificaAccountStudenteForm modAcc = (ModificaAccountStudenteForm)form;
        
        try {
       	
        	account = gestioneAccountController.getAccountStudente(request.getParameter("email"));
        	//request.setAttribute("account", account);
        	
        	modAcc.setNome(account.getNome());
        	modAcc.setCognome(account.getCognome());
        	modAcc.setMatricola(account.getMatricola());
        	modAcc.setNote(account.getNoteStud());
        	

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

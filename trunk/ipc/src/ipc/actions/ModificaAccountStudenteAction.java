package ipc.actions;

import ipc.control.GestioneAccountController;
import ipc.entity.Account;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * @version 	1.0
 * @author
 */
public class ModificaAccountStudenteAction extends Action

{
	
	private List<Account> elencoAccountStudenti;
	
	public List<Account> getElencoAccountStudenti() {
		return this.elencoAccountStudenti;
	}

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        GestioneAccountController gestioneAccountController = new GestioneAccountController();
        try {
        	elencoAccountStudenti = gestioneAccountController.getElencoAccountStudenti();
        	if(elencoAccountStudenti == null) {
        		errors.add("nome", new ActionError("elenco.studenti.no"));
        	} else {
        		messages.add("nome", new ActionMessage("elenco.studenti.ok"));
        		request.setAttribute("elencoAccountStudenti", elencoAccountStudenti);
        	}
        } catch (Exception e) {
            errors.add("name", new ActionError("generic.error"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        } else if(!messages.isEmpty()){
        	saveMessages(request, messages);
            forward = mapping.findForward("init");
        }
        return forward;
    }
}

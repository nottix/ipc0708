package ipc.actions;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * @version 	1.0
 * @author
 */
public class ModAccStudAction extends Action {

    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        GestioneAccountController control = new GestioneAccountController();
        ModificaAccountStudenteForm modAcc = (ModificaAccountStudenteForm)form;   
        try {
        	if(control.modificaAccountStudente(modAcc.getEmail(),
        									   modAcc.getNome(),
        									   modAcc.getCognome(),
        									   modAcc.getMatricola(),
        									   modAcc.getNote()) == true) {
        		messages.add("nome", new ActionMessage("account.studente.modificato.ok"));
    		} else {
    			errors.add("nome", new ActionError("account.studente.modificato.no"));
    		}
        } catch (Exception e) {
            errors.add("name", new ActionError("generic.error"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            forward = mapping.findForward("error");
        } else if(!messages.isEmpty()){
        	saveMessages(request, messages);
        	forward = mapping.findForward("success");
        }
        return forward;
    }
}

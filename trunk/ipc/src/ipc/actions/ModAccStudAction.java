package ipc.actions;

import ipc.forms.ModificaAccountStudenteForm;
import ipc.control.GestioneAccountController;

import java.util.Hashtable;

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
        GestioneAccountController gestioneAccountController = new GestioneAccountController();
        ModificaAccountStudenteForm modAcc = (ModificaAccountStudenteForm)form;   
        try {
        	Hashtable<String, Object> hash = new Hashtable<String, Object>();
        	hash.put("nome", modAcc.getNome());
        	hash.put("cognome", modAcc.getCognome());
        	hash.put("matricola", modAcc.getMatricola());
        	hash.put("noteStud", modAcc.getNote());
    		if(gestioneAccountController.modificaAccountStudente(modAcc.getEmail(), hash) == true) {
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

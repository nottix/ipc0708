package ipc.actions;

import ipc.control.CreazioneProfessoreController;
import ipc.forms.CreazioneProfessoreForm;

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
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class CreazioneProfessoreAction extends Action {
    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					throws Exception {
        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        CreazioneProfessoreController creazioneProfessoreController = new CreazioneProfessoreController();
        CreazioneProfessoreForm creazioneProfessoreForm = (CreazioneProfessoreForm)form;
        try {
        	if(creazioneProfessoreController.creazioneProfessore(creazioneProfessoreForm.getEmail(),
        														 creazioneProfessoreForm.getNome(),
        														 creazioneProfessoreForm.getCognome(),
        														 creazioneProfessoreForm.getPassword(),
        														 (creazioneProfessoreForm.getIsDirettore() != null) ? Boolean.TRUE : Boolean.FALSE,
        														 (creazioneProfessoreForm.getIsGestore() != null) ? Boolean.TRUE : Boolean.FALSE,
        														 null) == true) {
        		messages.add("nome", new ActionMessage("creazione.professore.ok"));
        	} else {
        		errors.add("nome", new ActionError("creazione.professore.no"));
        	}
        } catch (Exception e) {
            errors.add("nome", new ActionError("generic.error"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            forward = mapping.findForward("error");
        } else if(!messages.isEmpty()) {
        	saveMessages(request, messages);
            forward = mapping.findForward("success");
        }
        return forward;
    }
}

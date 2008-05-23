package ipc.actions;

import ipc.control.CreazioneProfessoreController;
import ipc.forms.CreazioneProfessoreForm;

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
        	Hashtable<String, Object> hash = new Hashtable<String, Object>();
        	hash.put("nome", creazioneProfessoreForm.getNome());
        	hash.put("cognome", creazioneProfessoreForm.getCognome());
        	hash.put("email", creazioneProfessoreForm.getEmail());
        	hash.put("password", creazioneProfessoreForm.getPassword());
        	hash.put("isDirettore", creazioneProfessoreForm.getIsDirettore()!=null ? Boolean.TRUE : Boolean.FALSE);
        	hash.put("isGestore", creazioneProfessoreForm.getIsGestore()!=null ? Boolean.TRUE : Boolean.FALSE);
        	if(creazioneProfessoreController.creazioneProfessore(hash) == true) {
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

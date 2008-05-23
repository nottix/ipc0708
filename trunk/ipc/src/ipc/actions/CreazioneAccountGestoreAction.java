package ipc.actions;

import ipc.forms.CreazioneAccountGestoreForm;
import ipc.control.GestioneAccountController;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author
 */
public class CreazioneAccountGestoreAction extends Action {

    public ActionForward execute(ActionMapping mapping,
    							 ActionForm form, 
    							 HttpServletRequest request, 
    							 HttpServletResponse response)
    							throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionMessages messages = new ActionMessages();
        ActionForward forward = new ActionForward();
        GestioneAccountController control = new GestioneAccountController();
        CreazioneAccountGestoreForm creazioneAccountForm = (CreazioneAccountGestoreForm)form;
        try {
        	Hashtable<String, Object> data = new Hashtable<String, Object>();
        	data.put("nome", creazioneAccountForm.getNome());
        	data.put("cognome", creazioneAccountForm.getCognome());
        	data.put("email", creazioneAccountForm.getEmail());
        	data.put("password", creazioneAccountForm.getPassword());
        	
            if(control.creazioneAccountGestore(data) == true) {
            	messages.add("name", new ActionMessage("account.created.ok"));	
            } else {
            	errors.add("name", new ActionError("account.created.no"));
            }
        } catch (Exception e) {
            errors.add("name", new ActionError("generic.error"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            forward = mapping.findForward("error");
        } else if(!messages.isEmpty()) {
        	saveMessages(request, messages);
        	forward = mapping.findForward("success");
        }
        return (forward);
    }
}

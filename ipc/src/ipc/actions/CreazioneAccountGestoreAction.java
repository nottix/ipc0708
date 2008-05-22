package ipc.actions;

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
import ipc.control.*;
import java.util.*;
import ipc.forms.*;

/**
 * @version 	1.0
 * @author
 */
public class CreazioneAccountGestoreAction extends Action

{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionMessages messages = new ActionMessages();
        ActionForward forward = new ActionForward(); // return value
        GestioneAccountController control = new GestioneAccountController();
        CreazioneAccountGestoreForm creazioneAccountForm = (CreazioneAccountGestoreForm)form;

        try {

        	Hashtable<String, Object> data = new Hashtable<String, Object>();
        	data.put("nome", creazioneAccountForm.getNome());
        	data.put("cognome", creazioneAccountForm.getCognome());
        	data.put("email", creazioneAccountForm.getEmail());
        	data.put("password", creazioneAccountForm.getPassword());
        	
            control.creazioneAccountGestore(data);
            
            forward = mapping.findForward("success");
            messages.add("name", new ActionMessage("account.created"));
            if(!messages.isEmpty()) {
            	saveMessages(request, messages);
            }

        } catch (Exception e) {

            // Report the error using the appropriate name and ID.
            errors.add("name", new ActionError("account.ncreated"));
            forward = mapping.findForward("error");

        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }

        return (forward);

    }
}

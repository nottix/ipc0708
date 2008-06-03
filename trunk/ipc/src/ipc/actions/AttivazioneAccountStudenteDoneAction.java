package ipc.actions;

import ipc.control.GestioneAccountController;

import java.util.Enumeration;

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
public class AttivazioneAccountStudenteDoneAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        GestioneAccountController gestioneAccountController = new GestioneAccountController();
        
        try {
        	Enumeration en = request.getParameterNames();
        	if(en.hasMoreElements() == false) {
        		errors.add("name", new ActionError("radio.button.error"));
        	} else {
        		while(en.hasMoreElements()) {
        			String name = (String)en.nextElement();
        			if (request.getParameter(name) != null && request.getParameter(name).trim().equals("on")) {
        				if(gestioneAccountController.abilitaAccountStudente(name) == true) {
        					messages.add("name", new ActionMessage("abilita.account.studente.ok"));
        				} else {
        					errors.add("name", new ActionError("abilita.account.studente.no"));
        				}
        			}
        		}
        	}
        } catch (Exception e) {
            errors.add("name", new ActionError("generic.error"));
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            forward = mapping.findForward("error");
            // Forward control to the appropriate 'failure' URI (change name as desired)
            //	forward = mapping.findForward(non riuscito");

        } else if(!messages.isEmpty()){
        	saveMessages(request, messages);
        	forward = mapping.findForward("success");
        }
        return (forward);

    }
}

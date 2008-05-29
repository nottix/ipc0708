package ipc.actions;

import ipc.control.GestioneStudenteController;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

public class IscrizioneCorsoAction extends Action {

    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        GestioneStudenteController control = new GestioneStudenteController();
        try {
        	if (isCancelled(request)) {
        		return mapping.findForward("main");
        	}
        	HttpSession session = request.getSession(false);
        	String idStudente = null;
        	if(session != null) {
        		idStudente = (String)session.getAttribute("email");
        	}
        	
        	Enumeration en = request.getParameterNames();
        	if(en.hasMoreElements() == false) {
        		errors.add("nome", new ActionError("radio.button.error"));
        	} else {
        		while(en.hasMoreElements()) {
        			String name = (String)en.nextElement();
        			if(name.equals("radio")) {
        				/**
        				 * TODO: ...e la data di iscrizione? ci serve a qualcosa?
        				 */
        				if(control.iscrizioneCorso(idStudente, request.getParameter(name)) == true) {
        					messages.add("nome", new ActionMessage("iscrizione.corso.ok"));
        				} else {
        					errors.add("nome", new ActionError("iscrizione.corso.no"));
        				}
        			}
        		}
        	}
        } catch (Exception e) {
            errors.add("name", new ActionError("generic.error"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            /**
             * TODO: e' giusto cosi'?!?
             */
            forward = mapping.findForward("success");
        } else if(!messages.isEmpty()) {
        	saveMessages(request, messages);
			forward = mapping.findForward("success");
        }
        return forward;
    }
}

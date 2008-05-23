package ipc.actions;

import ipc.forms.ConfermaIscrizioneCorsoForm;
import ipc.control.ConfermaIscrizioneController;

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

public class ConfermaIscrizioneCorsoDoneAction extends Action {
    public ActionForward execute(ActionMapping mapping, 
    		                     ActionForm form, 
    		                     HttpServletRequest request, 
    		                     HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        ConfermaIscrizioneController control = new ConfermaIscrizioneController();
        ConfermaIscrizioneCorsoForm cForm = (ConfermaIscrizioneCorsoForm)form;

        try {
        	if (isCancelled(request)) {
        		return mapping.findForward("main");
        	}

        	System.out.println("submit: "+cForm.getSubmit());
       		if(cForm.getSubmit().equals("Conferma")) {
       			System.out.println("conferma");
	            HttpSession session = request.getSession();
	            if(control.confermaIscrizioneCorso((Long)session.getAttribute("idIscrizioneCorso")) == true) {
	            	messages.add("nome", new ActionMessage("conferma.iscrizione.corso.ok"));
	            } else {
	            	errors.add("nome", new ActionError("conferma.iscrizione.corso.no"));
	            }
       		}
       		else if(cForm.getSubmit().equals("Rifiuta")) {
       			System.out.println("rifiuta");
	            HttpSession session = request.getSession();
	            if(control.rifiutaIscrizioneCorso((Long)session.getAttribute("idIscrizioneCorso")) == true) {
	            	messages.add("nome", new ActionMessage("rifiuta.iscrizione.corso.ok"));
	            } else {
	            	errors.add("nome", new ActionError("rifiuta.iscrizione.corso.no"));
	            }
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

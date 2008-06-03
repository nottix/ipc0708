package ipc.actions;

import ipc.control.ConfermaIscrizioneController;
import ipc.forms.ConfermaPrenotazioneEsameForm;

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

/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class ConfermaPrenotazioneEsameDoneAction extends Action {

    public ActionForward execute(ActionMapping mapping,
    							 ActionForm form, 
    							 HttpServletRequest request, 
    							 HttpServletResponse response)
    							throws Exception {

    	ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        ConfermaIscrizioneController control = new ConfermaIscrizioneController();
        ConfermaPrenotazioneEsameForm cForm = (ConfermaPrenotazioneEsameForm)form;
        try {
        	if (isCancelled(request)) {
        		return mapping.findForward("main");
        	}

        	System.out.println("submit: "+cForm.getSubmit());
       		if(cForm.getSubmit().equals("Conferma")) {
       			System.out.println("conferma");
	            HttpSession session = request.getSession();
	            if(control.confermaPrenotazioneEsame((Long)session.getAttribute("idPrenotazioneEsame")) == true) {
		            messages.add("name", new ActionMessage("prenotazione.confermata.ok"));	
	            } else {
	            	errors.add("name", new ActionError("prenotazione.confermata.no"));
	            }
       		}
       		else if(cForm.getSubmit().equals("Rifiuta")) {
       			System.out.println("rifiuta");
	            HttpSession session = request.getSession();
	            if(control.rifiutaPrenotazioneEsame((Long)session.getAttribute("idPrenotazioneEsame")) == true) {
		            messages.add("name", new ActionMessage("prenotazione.rifiutata.ok"));	
	            } else {
	            	errors.add("name", new ActionError("prenotazione.rifiutata.no"));
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
        return forward;
    }
}

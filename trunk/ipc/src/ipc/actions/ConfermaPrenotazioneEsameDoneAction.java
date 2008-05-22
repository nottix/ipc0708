package ipc.actions;

import ipc.control.ConfermaIscrizioneController;
import ipc.forms.*;

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
 * @author
 */
public class ConfermaPrenotazioneEsameDoneAction extends Action

{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        ConfermaIscrizioneController control = new ConfermaIscrizioneController();
        ConfermaPrenotazioneEsameForm cForm = (ConfermaPrenotazioneEsameForm)form;
        ActionMessages messages = new ActionMessages();

        try {
        	if (isCancelled(request)) {
        		return mapping.findForward("main");
        	}

        	System.out.println("submit: "+cForm.getSubmit());
       		if(cForm.getSubmit().equals("Conferma")) {
       			System.out.println("conferma");
	            HttpSession session = request.getSession();
	            control.confermaPrenotazioneEsame((Long)session.getAttribute("idPrenotazioneEsame"));
				forward = mapping.findForward("success");
	            messages.add("name", new ActionMessage("prenotazione.confermata"));
       		}
       		else if(cForm.getSubmit().equals("Rifiuta")) {
       			System.out.println("rifiuta");
	            HttpSession session = request.getSession();
	            control.rifiutaPrenotazioneEsame((Long)session.getAttribute("idPrenotazioneEsame"));
				forward = mapping.findForward("success");
	            messages.add("name", new ActionMessage("prenotazione.rifiutata"));
	            
       		}
       		
	        if(!messages.isEmpty()) {
	            saveMessages(request, messages);
	        }

        } catch (Exception e) {

            // Report the error using the appropriate name and ID.
            errors.add("name", new ActionError("generic.error"));

        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.

        if (!errors.isEmpty()) {
            saveErrors(request, errors);

            // Forward control to the appropriate 'failure' URI (change name as desired)
            //	forward = mapping.findForward(non riuscito");
            forward = mapping.findForward("error");

        }

        // Finish with
        return (forward);

    }
}

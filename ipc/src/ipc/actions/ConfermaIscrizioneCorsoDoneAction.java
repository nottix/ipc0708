package ipc.actions;

import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ipc.control.*;
import ipc.entity.*;
import java.util.*;

import ipc.forms.*;

/**
 * @version 	1.0
 * @author
 */
public class ConfermaIscrizioneCorsoDoneAction extends Action

{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
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
	            if(!control.confermaIscrizioneCorso((Long)session.getAttribute("idIscrizioneCorso")))
	            	errors.add("name", new ActionError("generic.error"));
       		}
       		else if(cForm.getSubmit().equals("Rifiuta")) {
       			System.out.println("rifiuta");
	            HttpSession session = request.getSession();
	            if(!control.rifiutaIscrizioneCorso((Long)session.getAttribute("idIscrizioneCorso")))
	            	errors.add("name", new ActionError("generic.error"));
       		}

        } catch (Exception e) {

            // Report the error using the appropriate name and ID.
            errors.add("name", new ActionError("id"));

        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.

        if (!errors.isEmpty()) {
            saveErrors(request, errors);

            // Forward control to the appropriate 'failure' URI (change name as desired)
            //	forward = mapping.findForward(non riuscito");
            forward = mapping.findForward("error");

        }
        else {
        	forward = mapping.findForward("success");
        }

        // Finish with
        return (forward);

    }
}

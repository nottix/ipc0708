package ipc.actions;

import ipc.control.ConfermaIscrizioneController;
import ipc.entity.*;
import ipc.forms.*;

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

/**
 * @version 	1.0
 * @author
 */
public class ConfermaPrenotazioneEsameAction extends Action

{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value

        try {
        	if (isCancelled(request)) {
        		return mapping.findForward("main");
        	}
        	Enumeration en = request.getParameterNames();
        	while(en.hasMoreElements()) {
        		String name = (String)en.nextElement();
        		if(name.equals("radio")) {
        			System.out.println("request: "+request.getParameter(name));
        			String idPrenotazioneEsame = request.getParameter(name);
        			Long id = Long.valueOf(idPrenotazioneEsame);
        			HttpSession session = request.getSession();
        			session.setAttribute("idPrenotazioneEsame", id);
        			
        			ConfermaPrenotazioneEsameForm cForm = (ConfermaPrenotazioneEsameForm)form;
        			ConfermaIscrizioneController control = new ConfermaIscrizioneController();
	        		PrenotazioneEsame pren = control.getPrenotazioneEsame(id);
        			cForm.setEmail(pren.getIdStudente());
        			cForm.setDataEsame(pren.getDataEsame().toString());
        			cForm.setDataPrenotazione(pren.getDataPrenotazione().toString());
        		}
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

        } else {

            // Forward control to the appropriate 'success' URI (change name as desired)
            forward = mapping.findForward("success");

        }

        // Finish with
        return (forward);

    }
}

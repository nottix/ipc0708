package ipc.actions;

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
import ipc.forms.*;
import ipc.control.*;
import ipc.entity.*;

/**
 * @version 	1.0
 * @author
 */
public class ConfermaIscrizioneCorsoAction extends Action

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
        			String idIscrizioneCorso = request.getParameter(name);
        			Long id = Long.valueOf(idIscrizioneCorso);
        			HttpSession session = request.getSession();
        			session.setAttribute("idIscrizioneCorso", id);
        			
        			ConfermaIscrizioneCorsoForm cForm = (ConfermaIscrizioneCorsoForm)form;
        			ConfermaIscrizioneController control = new ConfermaIscrizioneController();
	        		IscrizioneCorso iscr = control.getIscrizioneCorso(id);
        			cForm.setEmail(iscr.getIdStudente());
        			cForm.setDataIscrizione(iscr.getDataIscrizione().toString());
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

package ipc.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.*;
import ipc.control.*;
import ipc.entity.*;
import java.util.*;

/**
 * @version 	1.0
 * @author
 */
public class PrenotazioneEsameDoneAction extends Action

{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        GestioneStudenteController control = new GestioneStudenteController();
        
        try {

        	HttpSession session = request.getSession();
            Hashtable<String, Object> data = new Hashtable<String, Object>();
            data.put("idStudente", session.getAttribute("email"));
            data.put("idEsame", Long.valueOf((String)session.getAttribute("idEsame")));
            
            if(control.prenotazioneEsame(data)) {
            	session.removeAttribute("idEsame");
            	session.removeAttribute("idCorso");
            }
            else
            	errors.add("name", new ActionError("generic.error"));
            	

        } catch (Exception e) {

            // Report the error using the appropriate name and ID.
            errors.add("name", new ActionError("id"));

        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.

        if (!errors.isEmpty()) {
            saveErrors(request, errors);

            // Forward control to the appropriate 'failure' URI (change name as desired)
            forward = mapping.findForward("error");

        } else {

            // Forward control to the appropriate 'success' URI (change name as desired)
            forward = mapping.findForward("success");

        }

        // Finish with
        return (forward);

    }
}

package ipc.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.*;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ipc.control.*;
import java.util.*;
import ipc.entity.*;

/**
 * @version 	1.0
 * @author
 */
public class ConfermaIscrizioneElencoAction extends Action

{
	
	private List<Corso> elencoCorsi;
	
	public List<Corso> getElencoCorsi() {
		return this.elencoCorsi;
	}

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        ConfermaIscrizioneController control = new ConfermaIscrizioneController();
        
        try {

        	HttpSession session = request.getSession();
            elencoCorsi = control.getElencoCorsiAccedibili((String)session.getAttribute("email"));
            System.out.println("elencoCorsi size: "+elencoCorsi.size());
            request.setAttribute("elencoCorsi", elencoCorsi);

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
            forward = mapping.findForward("init");

        }

        // Finish with
        return (forward);

    }
}

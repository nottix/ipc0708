package ipc.actions;

import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ipc.control.*;
import ipc.entity.*;
import java.util.*;

/**
 * @version 	1.0
 * @author
 */
public class ConfermaIscrizioneCorsoElencoAction extends Action

{
	
	private List<IscrizioneCorso> elencoIscrizioniCorso;
	
	public List<IscrizioneCorso> getElencoIscrizioniCorso() {
		return this.elencoIscrizioniCorso;
	}

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        ConfermaIscrizioneController control = new ConfermaIscrizioneController();

        try {

        	HttpSession session = request.getSession();
            this.elencoIscrizioniCorso = control.getElencoIscrizioniCorso((String)session.getAttribute("acronimo"));
            System.out.println("size iscr: "+this.elencoIscrizioniCorso.size());
            request.setAttribute("elencoIscrizioniCorso", this.elencoIscrizioniCorso);

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

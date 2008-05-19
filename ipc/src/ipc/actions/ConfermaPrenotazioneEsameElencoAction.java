package ipc.actions;

import ipc.control.ConfermaIscrizioneController;
import ipc.entity.*;

import java.util.List;

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
public class ConfermaPrenotazioneEsameElencoAction extends Action

{
private List<PrenotazioneEsame> elencoPrenotazioniEsami;
	
	public List<PrenotazioneEsame> getElencoPrenotazioniEsami() {
		return this.elencoPrenotazioniEsami;
	}

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        ConfermaIscrizioneController control = new ConfermaIscrizioneController();

        try {
        	System.out.println("ssss");
        	HttpSession session = request.getSession();
            this.elencoPrenotazioniEsami = control.getPrenotazioniEsame((String)session.getAttribute("acronimo"));
            System.out.println("size pren: "+this.elencoPrenotazioniEsami.size());
            request.setAttribute("elencoPrenotazioniEsami", this.elencoPrenotazioniEsami);

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

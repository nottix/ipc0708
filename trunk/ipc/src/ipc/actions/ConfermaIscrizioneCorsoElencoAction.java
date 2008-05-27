package ipc.actions;

import ipc.entity.IscrizioneCorso;
import ipc.control.ConfermaIscrizioneController;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class ConfermaIscrizioneCorsoElencoAction extends Action {
	
	private List<IscrizioneCorso> elencoIscrizioniCorso;
	
	public List<IscrizioneCorso> getElencoIscrizioniCorso() {
		return this.elencoIscrizioniCorso;
	}

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        ConfermaIscrizioneController control = new ConfermaIscrizioneController();
        try {
        	HttpSession session = request.getSession();
            this.elencoIscrizioniCorso = control.getElencoIscrizioniCorso((String)session.getAttribute("acronimo"));
            if(this.elencoIscrizioniCorso == null) {
            	errors.add("nome", new ActionError("elenco.iscrizione.corso.no"));
            } else {
            	messages.add("nome", new ActionMessage("elenco.iscrizione.corso.ok"));
                System.out.println("size iscr: "+this.elencoIscrizioniCorso.size());
                request.setAttribute("elencoIscrizioniCorso", this.elencoIscrizioniCorso);
            }
        } catch (Exception e) {
            errors.add("name", new ActionError("id"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        } else if(!messages.isEmpty()) {
        	saveMessages(request, messages);
            forward = mapping.findForward("init");
        }
        return (forward);
    }
}

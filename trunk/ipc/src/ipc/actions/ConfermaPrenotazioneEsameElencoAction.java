package ipc.actions;

import ipc.control.ConfermaIscrizioneController;
import ipc.entity.PrenotazioneEsame;

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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class ConfermaPrenotazioneEsameElencoAction extends Action {
	private List<PrenotazioneEsame> elencoPrenotazioniEsami;
	
	public List<PrenotazioneEsame> getElencoPrenotazioniEsami() {
		return this.elencoPrenotazioniEsami;
	}

    public ActionForward execute(ActionMapping mapping,
    							 ActionForm form, 
    							 HttpServletRequest request, 
    							 HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        ConfermaIscrizioneController control = new ConfermaIscrizioneController();

        try {
        	HttpSession session = request.getSession();
        	System.out.println("Acronimo: " + session.getAttribute("acronimo"));
            this.elencoPrenotazioniEsami = control.getPrenotazioniEsame((String)session.getAttribute("acronimo"));
            if(this.elencoPrenotazioniEsami == null) {
            	errors.add("nome", new ActionError("elenco.prenotazione.esami.no"));
            } else {
            	messages.add("nome", new ActionMessage("elenco.prenotazione.esami.ok"));
            	System.out.println("size pren: "+this.elencoPrenotazioniEsami.size());
            	request.setAttribute("elencoPrenotazioniEsami", this.elencoPrenotazioniEsami);
            }
        } catch (Exception e) {
        	errors.add("name", new ActionError("id"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        } else if(!messages.isEmpty()){
        	saveMessages(request, messages);
            forward = mapping.findForward("init");
        }
        return forward;
    }
}

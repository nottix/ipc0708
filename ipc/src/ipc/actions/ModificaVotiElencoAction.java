package ipc.actions;

import ipc.control.GestioneEsameController;
import ipc.entity.PrenotazioneEsame;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
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
public class ModificaVotiElencoAction extends Action

{
	
	private List<PrenotazioneEsame> elencoPrenotazioniEsami;
	
	public List<PrenotazioneEsame> getElencoPrenotazioniEsami() {
		return this.elencoPrenotazioniEsami;
	}

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        GestioneEsameController control = new GestioneEsameController();
        try {
        	String acronimo = (String)request.getSession().getAttribute("acronimo");
        	elencoPrenotazioniEsami = control.getElencoPrenotazioniEsamiCorso(acronimo);
        	if(elencoPrenotazioniEsami == null) {
        		errors.add("name", new ActionError("elenco.prenotazioni.esami.corso.no"));
        	} else {
        		messages.add("name", new ActionMessage("elenco.prenotazioni.esami.corso.ok"));
        		request.setAttribute("elencoPrenotazioniEsami", elencoPrenotazioniEsami);
        	}
        } catch (Exception e) {
            errors.add("name", new ActionError("generic.error"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        } else if(!messages.isEmpty()) {
        	saveMessages(request, messages);
            forward = mapping.findForward("success");
        }
        return forward;
    }
}

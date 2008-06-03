package ipc.actions;

import ipc.control.GestioneCorsoController;
import ipc.entity.Account;
import ipc.entity.Corso;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class ModificaCorsoAction extends Action {

	private List<Account> elencoProfessori = null;
	
	private Corso corso = null;
	
	public List<Account> getElencoProfessori() {
		return this.elencoProfessori;
	}
	
	public Corso getCorso() {
		return this.corso;
	}

	public ActionForward execute(ActionMapping mapping,
								 ActionForm form,
								 HttpServletRequest request,
								 HttpServletResponse response)
								throws Exception {
		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		ActionMessages messages = new ActionMessages();
		GestioneCorsoController control = new GestioneCorsoController();
		try {
			String old_acronimo = (String) request.getSession().getAttribute("old_acronimo");
        	if((old_acronimo == null) || (old_acronimo.length() == 0)) {
        		System.out.println("old_acronimo: " + old_acronimo);
        		errors.add("nome", new ActionError("session.error"));
        	} else {
        		System.out.println("old_acronimo :: " + old_acronimo);
        		messages.add("nome", new ActionMessage("session.ok"));
	        	this.corso = control.getCorso(old_acronimo);
	        	if(this.corso == null) {
	        		System.out.println("Errore corso");
	        		errors.add("nome", new ActionError("corso.error"));
	        	} else {
	        		request.setAttribute("corso", corso);
	        		elencoProfessori = control.getElencoAccountProfessori();
	        		request.setAttribute("elencoProfessori", elencoProfessori);
	        	}
	        }
		} catch (Exception e) {
			errors.add("name", new ActionError("generic.error"));
		}
		if (!errors.isEmpty()) {
			saveErrors(request, errors);
			forward = mapping.findForward("error");
		} else if(!messages.isEmpty()){
			saveMessages(request, messages);
			forward = mapping.findForward("success");
		}
		return forward;
	}
}

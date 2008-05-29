package ipc.actions;

import ipc.control.GestioneCorsoController;
import ipc.forms.ModificaCorsoForm;
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
 * @author
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

        ModificaCorsoForm modificaCorso = (ModificaCorsoForm) form;
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
//        	Enumeration en = request.getParameterNames();
//        	if(en.hasMoreElements() == false) {
//        		errors.add("nome", new ActionError("radio.button.error"));
//        	} else {
//        		while(en.hasMoreElements()) {
//        			String name = (String)en.nextElement();
//        			if (request.getParameter(name) != null && request.getParameter(name).trim().equals("on")) {
//        				if(name.indexOf("titolare")>=0) {
//        					titolari.add(name.substring(name.indexOf("-")+1));
//        					titolareCounter++;
//        				}
//        				if(name.indexOf("collaboratore")>=0) {
//        					collaboratori.add(name.substring(name.indexOf("-")+1));
//        					collaboratoreCounter++;
//        				}
//        			}
//        		}
//
//        		if(titolareCounter>1) {
//        			if(collaboratoreCounter>1)
//        				data.put("elencoCollaboratori", collaboratori);
//        			data.put("elencoTitolari", titolari);
//        			data.put("nome", modificaCorso.getNome());
//        			data.put("acronimo", modificaCorso.getAcronimo());
//        			data.put("descrizione", modificaCorso.getDescrizione());
//        			data.put("dataApertura", new SimpleDateFormat("MM/dd/yy").parse(modificaCorso.getDataApertura()));
//        			data.put("dataChiusura", new SimpleDateFormat("MM/dd/yy").parse(modificaCorso.getDataChiusura()));
//        			if(control.modificaCorso(old_acronimo, data) == true) {
//        				messages.add("nome", new ActionMessage("modifica.corso.ok"));
//        			} else {
//        				errors.add("nome", new ActionError("modifica.corso.no"));
//        			}
//        		}
//        		elencoProfessori = control.getElencoAccountProfessori();
//        		if(elencoProfessori == null) {
//        			errors.add("nome", new ActionError("elenco.account.professore.no"));
//        		} else {
//        			messages.add("nome", new ActionMessage("elenco.account.professore.ok"));
//        			request.setAttribute("elencoProfessori", elencoProfessori);	
//        		}
//        	}
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

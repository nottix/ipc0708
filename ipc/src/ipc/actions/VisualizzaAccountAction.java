package ipc.actions;

import ipc.control.GestioneAccountController;
import ipc.forms.VisualizzaAccountForm;
import ipc.entity.Account;
import ipc.entity.Corso;

import java.util.List;
import java.util.Enumeration;

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
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class VisualizzaAccountAction extends Action {
	
	private Account account;
	
	private List<Corso> elencoCorsiTitolare;
	
	private List<Corso> elencoCorsiCollaboratore;
	
	public Account getAccount() {
		return this.account;
	}
	
	public List<Corso> getElencoCorsiTitolare() {
		return this.elencoCorsiTitolare;
	}
	
	public List<Corso> elencoElencoCorsiCollaboratore() {
		return this.elencoCorsiCollaboratore;
	}

    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form, 
    							 HttpServletRequest request, 
    							 HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        GestioneAccountController control = new GestioneAccountController();
        VisualizzaAccountForm cForm = (VisualizzaAccountForm)form;
        boolean radio_is_enabled = false;
        try {
        	System.out.println("here");
        	if (isCancelled(request)) {
        		return mapping.findForward("main");
        	}
        	Enumeration en = request.getParameterNames();
        	if(en.hasMoreElements() == false) {
        		errors.add("nome", new ActionError("radio.button.error"));
        	} else {
        		System.out.println("oooook");
        		while(en.hasMoreElements()) {
        			String name = (String)en.nextElement();
        			if(name.equals("radio")) {
        				radio_is_enabled = true;
        				System.out.println("request: "+request.getParameter(name));
        				//GestioneCorsoForm gestForm = (GestioneCorsoForm)form;
        				//gestForm.setAcronimo(request.getParameter(name));
        				String email = request.getParameter(name);
        				account = control.getAccount(email);
        				if(account == null ) {
        					errors.add("nome", new ActionError("account.error"));
        				} else {
        					messages.add("nome", new ActionMessage("account.ok"));
        					request.setAttribute("account", account);
        					cForm.setNome(account.getNome());
        					cForm.setCognome(account.getCognome());
        					cForm.setEmail(account.getEmail());
        					if(account.getTipologia().equals("studente")) {
        						cForm.setNote(account.getNoteStud());
        						cForm.setMatricola(account.getMatricola());
        					}
        					if(account.getTipologia().equals("professore")) {
        						cForm.setNote(account.getNoteProf());
        						this.elencoCorsiTitolare = control.getCorsiWhereIsTitolare(account.getEmail());
        						if(this.elencoCorsiTitolare == null) {
        							errors.add("nome", new ActionError("titolari.corso.no"));
        						} else {
        							messages.add("nome", new ActionMessage("titolari.corso.ok"));
        							request.setAttribute("elencoCorsiTitolare", elencoCorsiTitolare);	
        						}
        						this.elencoCorsiCollaboratore = control.getCorsiWhereIsCollaboratore(account.getEmail());
        						if(this.elencoCorsiCollaboratore == null) {
        							errors.add("nome", new ActionError("collaboratori.corso.no"));
        						} else {
        							messages.add("nome", new ActionMessage("collaboratori.corso.ok"));
        							request.setAttribute("elencoCorsiCollaboratore", elencoCorsiCollaboratore);
        						}
        					}
        				}
        			}
        		}
        		if(radio_is_enabled == false)
        			errors.add("nome", new ActionError("dev.null"));
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

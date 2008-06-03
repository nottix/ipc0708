package ipc.actions;

import ipc.control.GestioneStudenteController;
import ipc.entity.Account;
import ipc.entity.Esame;
import ipc.entity.Progetto;
import ipc.forms.VisualizzaCorsoElencoForm;

import java.util.Enumeration;
import java.util.List;

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
public class VisualizzaCorsoAction extends Action {
	private List<Progetto> elencoProgetti = null;
	
	public List<Progetto> getElencoProgetti() {
		return this.elencoProgetti;
	}

	private List<Esame> elencoEsami = null;
	
	public List<Esame> getElencoEsami() {
		return this.elencoEsami;
	}
	
	private List<Account> elencoTitolari;
	private List<Account> elencoCollaboratori;

	public List<Account> getElencoTitolari() {
		return this.elencoTitolari;
	}
	
	public List<Account> getElencoCollaboratori() {
		return this.elencoCollaboratori;
	}

    public ActionForward execute(ActionMapping mapping,
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        String map = "";
        GestioneStudenteController control = new GestioneStudenteController();
        VisualizzaCorsoElencoForm cForm = (VisualizzaCorsoElencoForm) form;
        try {
        	Enumeration en = request.getParameterNames();
        	if(en.hasMoreElements() == false) {
        		errors.add("nome", new ActionError("radio.button.error"));
        	} else {
        		while(en.hasMoreElements()) {
        			String name = (String)en.nextElement();
        			if(name.equals("radio")) {
        				String acronimo = request.getParameter(name);
        				request.setAttribute("acronimo", acronimo);
        				request.getSession().setAttribute("acronimo", acronimo);
        				if(cForm.getSubmit().equals("Visualizza Informazioni Corso")) {
        					this.elencoTitolari = control.getElencoTitolariCorso(acronimo);
        					if(this.elencoTitolari == null) {
    							errors.add("nome", new ActionError("titolari.corso.no"));
    						} else {
    							messages.add("nome", new ActionMessage("titolari.corso.ok"));
    							request.getSession().setAttribute("elencoTitolari", elencoTitolari);
    							map = "vicorso";
    						}
    						this.elencoCollaboratori = control.getElencoCollaboratoriCorso(acronimo);
    						if(this.elencoCollaboratori == null) {
    							errors.add("nome", new ActionError("collaboratori.corso.no"));
    						} else {
    							messages.add("nome", new ActionMessage("collaboratori.corso.ok"));
    							request.getSession().setAttribute("elencoCollaboratori", elencoCollaboratori);
    							map = "vicorso";
    						}
                		} else if(cForm.getSubmit().equals("Visualizza Informazioni Progetti")) {
                			this.elencoProgetti = control.getElencoProgettiDispAttiviCorso(acronimo);
                			if(elencoProgetti == null) {
                				errors.add("nome", new ActionError("elenco.progetti.no"));
                			} else {
                				messages.add("nome", new ActionMessage("elenco.progetti.ok"));
                				request.getSession().setAttribute("elencoProgetti", this.elencoProgetti);
                				map = "viprogetti";
                			}
                		} else if(cForm.getSubmit().equals("Visualizza Informazioni Esami")) {
                			this.elencoEsami = control.getElencoEsamiDispAttiviCorso(acronimo);
                			if(this.elencoEsami == null) {
                				errors.add("nome", new ActionError("elenco.esami.no"));
                			} else {
                				messages.add("nome", new ActionMessage("elenco.esami.ok"));
                            	request.getSession().setAttribute("elencoEsami", this.elencoEsami);
                            	map = "viesami";
                			}
                		}
        			}
        		}
            }
        } catch (Exception e) {
            errors.add("name", new ActionError("id"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            forward = mapping.findForward("error");
        } else if(!messages.isEmpty()) {
        	saveMessages(request, messages);
            forward = mapping.findForward(map);
        }
        return forward;
    }
}

package ipc.actions;

import ipc.control.GestioneCorsoController;
import ipc.entity.Account;
import ipc.forms.ModificaCorsoForm;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author
 */
public class ModificaCorsoDoneAction extends Action

{
	private List<Account> elencoProfessori = null;
	
	public List<Account> getElencoProfessori() {
		return this.elencoProfessori;
	}
	
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        Hashtable<String, Object> data = new Hashtable<String, Object>();
        GestioneCorsoController control = new GestioneCorsoController();
        HashSet<String> collaboratori = new HashSet<String>();
        HashSet<String> titolari = new HashSet<String>();
        ModificaCorsoForm modificaCorso = (ModificaCorsoForm) form;
        try {
        	int titolareCounter = 0;
        	int collaboratoreCounter = 0;
        	String old_acronimo = (String) request.getSession().getAttribute("old_acronimo");
        	if((old_acronimo == null) || (old_acronimo.length() == 0)) {
        		errors.add("nome", new ActionError("session.error"));
        	}
        	System.out.println("session: " + old_acronimo);
        	Enumeration en = request.getParameterNames();
        	if(en.hasMoreElements() == false) {
        		System.out.println("non ha molti elementi...");
        		errors.add("nome", new ActionError("radio.button.error"));
        	} else {
        		while(en.hasMoreElements()) {
        			String name = (String)en.nextElement();
        			System.out.println("Parameter: " + name);
        			if (request.getParameter(name) != null && request.getParameter(name).trim().equals("on")) {
        				if(name.indexOf("titolare")>=0) {
        					titolari.add(name.substring(name.indexOf("-")+1));
        					titolareCounter++;
        				}
        				if(name.indexOf("collaboratore")>=0) {
        					collaboratori.add(name.substring(name.indexOf("-")+1));
        					collaboratoreCounter++;
        				}
        			}
        		}
        		System.out.println("titolari: " + titolareCounter);
        		System.out.println("collaboratori: " + collaboratoreCounter);
        		if(titolareCounter > 1)
        			data.put("elencoTitolari", titolari);
        		if(collaboratoreCounter>1)
        			data.put("elencoCollaboratori", collaboratori);
        	
        		data.put("nome", modificaCorso.getNome());
        		data.put("acronimo", modificaCorso.getAcronimo());
        		data.put("descrizione", modificaCorso.getDescrizione());
        		data.put("dataApertura", new SimpleDateFormat("yyyy-dd-MM hh:mm:ss").parse(modificaCorso.getDataApertura()));
        		data.put("dataChiusura", new SimpleDateFormat("yyyy-dd-MM hh:mm:ss").parse(modificaCorso.getDataChiusura()));
        		data.put("comunicazioni", modificaCorso.getComunicazioni());
        		if(control.modificaCorso(old_acronimo, data) == true) {
        			messages.add("nome", new ActionMessage("modifica.corso.ok"));
        		} else {
        			errors.add("nome", new ActionError("modifica.corso.no"));
        		}
        		elencoProfessori = control.getElencoAccountProfessori();
        		if(elencoProfessori == null) {
        			errors.add("nome", new ActionError("elenco.account.professore.no"));
        		} else {
        			messages.add("nome", new ActionMessage("elenco.account.professore.ok"));
        			request.setAttribute("elencoProfessori", elencoProfessori);	
        		}
        	}
        } catch (Exception e) {
            errors.add("name", new ActionError("generic.error"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            forward = mapping.findForward("error");
        } else if(!messages.isEmpty()) {
        	saveMessages(request, messages);
        	forward = mapping.findForward("success");
        }
        return forward;
    }
}

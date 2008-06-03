package ipc.actions;

import ipc.control.GestioneStudenteController;

import ipc.entity.Esame;

import java.util.Enumeration;
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
public class PrenotazioneEsameElencoAction extends Action {
	
	private List<Esame> elencoEsami;
	
	public List<Esame> getElencoEsami() {
		return elencoEsami;
	}

    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form, 
    							 HttpServletRequest request, 
    							 HttpServletResponse response)
            					throws Exception {

    	ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        GestioneStudenteController control = new GestioneStudenteController();
        try {
        	if (isCancelled(request)) {
        		return mapping.findForward("main");
        	}
        	Enumeration en = request.getParameterNames();
        	if(en.hasMoreElements() == false) {
        		errors.add("nome", new ActionError("radio.button.error"));
        	} else {
        		while(en.hasMoreElements()) {
        			String name = (String)en.nextElement();
        			if(name.equals("radio")) {
        				System.out.println("request: "+request.getParameter(name));
        				HttpSession session = request.getSession();
        				session.setAttribute("idCorso", request.getParameter(name));
        				this.elencoEsami = control.getElencoEsamiDispAttivi();
        				if(elencoEsami == null) {
        					errors.add("nome", new ActionError("elenco.esami.disponibili.attivi.no"));
        				} else {
        					messages.add("nome", new ActionMessage("elenco.esami.disponibili.attivi.ok"));
        					request.setAttribute("elencoEsami", elencoEsami);
        				}
        			}
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

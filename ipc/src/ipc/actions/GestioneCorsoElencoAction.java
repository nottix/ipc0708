package ipc.actions;

import ipc.control.GestioneCorsoController;
import ipc.entity.Corso;

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
public class GestioneCorsoElencoAction extends Action {
	
	private List<Corso> elencoCorsi = null;
	
	public List<Corso> getElencoCorsi() {
		return this.elencoCorsi;
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
        	elencoCorsi = control.getElencoCorsi();
        	if(elencoCorsi == null) {
        		errors.add("nome", new ActionError("elenco.corsi.no"));
        	} else {
        		messages.add("nome", new ActionMessage("elenco.corsi.ok"));
                request.setAttribute("elencoCorsi", elencoCorsi);
        	}
        } catch (Exception e) {
            errors.add("name", new ActionError("generic.error"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            forward = mapping.findForward("error");
        } else {
        	saveMessages(request, messages);
            forward = mapping.findForward("init");
        }
        return forward;
    }
}

package ipc.actions;

import ipc.forms.ModificaVotiForm;
import ipc.control.GestioneEsameController;
import ipc.entity.PrenotazioneEsame;

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
 * @author
 */
public class ModificaVotiAction extends Action {

    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        GestioneEsameController control = new GestioneEsameController();
        try {
        	if (isCancelled(request)) {
        		return mapping.findForward("main");
        	}
        	Enumeration en = request.getParameterNames();
        	while(en.hasMoreElements()) {
        		String name = (String)en.nextElement();
        		if(name.equals("radio")) {
        			PrenotazioneEsame prenotazione = control.getPrenotazioneEsame(Long.valueOf(request.getParameter(name)));
        			if(prenotazione == null) {
        				errors.add("nome", new ActionError("prenotazione.error"));
        			} else {
        				messages.add("nome", new ActionMessage("prenotazione.ok"));
        				ModificaVotiForm cForm = (ModificaVotiForm)form;
	        			cForm.setIdStudente(prenotazione.getIdStudente());
	        			cForm.setDataEsame(prenotazione.getDataEsame().toString());
	        			cForm.setDataPrenotazione(prenotazione.getDataPrenotazione().toString());
	        			if(prenotazione.getEsaminatore()!=null)
	        				cForm.setEsaminatore(prenotazione.getEsaminatore());
	        			if(prenotazione.getNota()!=null)
	        				cForm.setNota(prenotazione.getNota());
	        			if(prenotazione.getPresenzaEsame()!=null)
	        				cForm.setPresenzaEsame(prenotazione.getPresenzaEsame()?"on":"off");
	        			if(prenotazione.getVotoAccettato()!=null)
	        				cForm.setVotoAccettato(prenotazione.getVotoAccettato()?"on":"off");
	        			if(prenotazione.getVotoEsame()!=null)
	        				cForm.setVotoEsame(prenotazione.getVotoEsame());
	        			request.getSession().setAttribute("idPrenotazioneEsame", Long.valueOf(request.getParameter(name)));
        			}
        		}
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

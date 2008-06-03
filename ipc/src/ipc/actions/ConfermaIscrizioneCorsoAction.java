package ipc.actions;

import ipc.forms.ConfermaIscrizioneCorsoForm;
import ipc.control.ConfermaIscrizioneController;
import ipc.entity.IscrizioneCorso;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;

/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class ConfermaIscrizioneCorsoAction extends Action {

    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        ActionMessages messages = new ActionMessages();

        try {
        	if (isCancelled(request)) {
        		return mapping.findForward("main");
        	}
        	Enumeration en = request.getParameterNames();
        	if(en.hasMoreElements() == false) {
        		errors.add("name", new ActionError("radio.button.error"));
        	} else {
        		while(en.hasMoreElements()) {
        			String name = (String)en.nextElement();
        			if(name.equals("radio")) {
        				System.out.println("request: " + request.getParameter(name));
        				String idIscrizioneCorso = request.getParameter(name);
        				Long id = Long.valueOf(idIscrizioneCorso);
        				HttpSession session = request.getSession();
        				session.setAttribute("idIscrizioneCorso", id);

        				ConfermaIscrizioneCorsoForm cForm = (ConfermaIscrizioneCorsoForm)form;
        				ConfermaIscrizioneController control = new ConfermaIscrizioneController();
        				IscrizioneCorso iscr = control.getIscrizioneCorso(id);
        				if(iscr == null) {
        					errors.add("nome", new ActionError("conferma.iscrizione.corso.no"));
        				} else {
        					messages.add("nome", new ActionMessage("conferma.iscrizione.corso.ok"));
        					cForm.setEmail(iscr.getIdStudente());
        					cForm.setDataIscrizione(iscr.getDataIscrizione().toString());	
        				}
        			}
        		}
        	}
        } catch (Exception e) {
            errors.add("name", new ActionError("id"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        } else if(!messages.isEmpty()){
        	saveMessages(request, messages);
            forward = mapping.findForward("success");
        }
        return (forward);
    }
}

package ipc.actions;

import ipc.forms.CreazioneEsameForm;

import java.util.Enumeration;

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
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class CreazioneEsameAction extends Action {

    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        try {
        	System.out.println("ok");
        	Enumeration en = request.getParameterNames();
        	while(en.hasMoreElements()) {
        		String name = (String)en.nextElement();
        		System.out.println("name: " + name + ", value: " + request.getParameter(name));
        		CreazioneEsameForm cForm = ((CreazioneEsameForm)form);
        		System.out.println("Parameter " + request.getParameter(name));
        		if(request.getParameter(name) == null) {
        			errors.add("name", new ActionError("radio.button.error"));
        		} else {
        			messages.add("name", new ActionMessage("creazione.esame.ok"));
            		cForm.setAcronimo(request.getParameter(name));
        		}
        	}
        } catch (Exception e) {
            errors.add("name", new ActionError("id"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            forward = mapping.findForward("error");
        } else if (!messages.isEmpty()){
        	saveMessages(request, messages);
        	forward = mapping.findForward("success");
        }
        return (forward);

    }
}

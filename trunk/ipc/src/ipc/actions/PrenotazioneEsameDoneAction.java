package ipc.actions;

import ipc.control.GestioneStudenteController;

import java.util.Hashtable;

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
 * @author
 */
public class PrenotazioneEsameDoneAction extends Action {

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
        	HttpSession session = request.getSession();
            Hashtable<String, Object> data = new Hashtable<String, Object>();
            data.put("idStudente", session.getAttribute("email"));
            data.put("idEsame", Long.valueOf((String)session.getAttribute("idEsame")));
            
            if(control.prenotazioneEsame(data) == false) {
            	errors.add("nome", new ActionError("prenotazione.esame.no"));
            } else {
            	messages.add("nome", new ActionMessage("prenotazione.esame.ok"));
                session.removeAttribute("idEsame");
                session.removeAttribute("idCorso");
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

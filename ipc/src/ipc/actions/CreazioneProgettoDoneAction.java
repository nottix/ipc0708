package ipc.actions;

import ipc.control.GestioneEsameController;
import ipc.forms.CreazioneProgettoForm;

import java.text.SimpleDateFormat;
import java.util.Hashtable;

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
public class CreazioneProgettoDoneAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        GestioneEsameController cont = new GestioneEsameController();
        CreazioneProgettoForm cForm = (CreazioneProgettoForm)form;
        try {
        	Hashtable<String, Object> data = new Hashtable<String, Object>();
            data.put("acronimo", cForm.getAcronimo());
            data.put("titolo", cForm.getTitolo());
            data.put("dataConsegna", new SimpleDateFormat("MM/dd/yy").parse(cForm.getDataConsegna()));
            data.put("maxUploadPerStudente", cForm.getMaxUploadPerStudente());
        	data.put("maxDimGruppo", cForm.getMaxDimGruppo());
            if(cont.creazioneProgetto(data) == true) {
            	messages.add("nome", new ActionMessage("iscrizione.corso.ok"));
            } else {
            	errors.add("nome", new ActionError("iscrizione.corso.no"));
            }
        } catch (Exception e) {
            errors.add("name", new ActionError("generic.error"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            /**
             * TODO: E' corretto questo?!?!?
             */
            forward = mapping.findForward("success");
        } else if(!messages.isEmpty()) {
        	saveMessages(request, messages);
        	forward = mapping.findForward("success");
        }
        return (forward);
    }
}

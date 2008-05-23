package ipc.actions;

import ipc.control.GestioneCorsoController;
import ipc.forms.CreazioneEsameForm;

import java.util.Hashtable;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
 * @author
 */
public class CreazioneEsameDoneAction extends Action {

    public ActionForward execute(ActionMapping mapping,
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        GestioneCorsoController cont = new GestioneCorsoController();
        CreazioneEsameForm cForm = (CreazioneEsameForm)form;
        try {
        	Hashtable<String, Object> data = new Hashtable<String, Object>();
            data.put("acronimo", cForm.getAcronimo());
            data.put("dataInizioPeriodoPrenotazione", new SimpleDateFormat("MM/dd/yy").parse(cForm.getDataInizio()));
            data.put("dataFinePeriodoPrenotazione", new SimpleDateFormat("MM/dd/yy").parse(cForm.getDataFine()));
            data.put("dataEsame", new SimpleDateFormat("MM/dd/yy").parse(cForm.getDataEsame()));
        	data.put("auleEsame", cForm.getAule());
        	System.out.println("aulaEsame: "+cForm.getAule());
            if(cont.creazioneEsame(data) == true) {
            	request.getSession().removeAttribute("acronimo");
            	messages.add("nome", new ActionMessage("creazione.esame.ok"));
            } else {
            	errors.add("nome", new ActionError("creazione.esame.no"));
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
        return (forward);
    }
}

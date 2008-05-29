package ipc.actions;

import ipc.control.GestioneEsameController;
import ipc.forms.ModificaVotiForm;

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

public class ModificaVotiDoneAction extends Action {

    public ActionForward execute(ActionMapping mapping,
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        GestioneEsameController control = new GestioneEsameController();
        ModificaVotiForm cForm = (ModificaVotiForm)form;

        try {
        	if(control.modificaVoto((Long)request.getSession().getAttribute("idPrenotazioneEsame"),
        							cForm.getVotoEsame(),
        							cForm.getEsaminatore(),
        							(cForm.getPresenzaEsame() != null) ? Boolean.TRUE : Boolean.FALSE,
        							(cForm.getVotoAccettato() != null) ? Boolean.TRUE : Boolean.FALSE,
        							cForm.getNota()) == true) {
            	messages.add("nome", new ActionMessage("voto.modificato.ok"));
            	request.getSession().removeAttribute("idPrenotazioneEsame");
            } else {
            	errors.add("nome", new ActionMessage("voto.modificato.no"));
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

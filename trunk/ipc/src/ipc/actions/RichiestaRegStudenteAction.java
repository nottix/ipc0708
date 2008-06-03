package ipc.actions;

import ipc.control.LoginController;
import ipc.forms.RichiestaRegStudenteForm;

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
public class RichiestaRegStudenteAction extends Action {

    public ActionForward execute(ActionMapping mapping,
    							 ActionForm form, 
    							 HttpServletRequest request,
    							 HttpServletResponse response)
    							throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); 
        ActionMessages messages = new ActionMessages();
        try {
        	RichiestaRegStudenteForm regRequestStudentForm = (RichiestaRegStudenteForm) form;
            LoginController loginController = new LoginController();
            if(loginController.richiestaRegStudente(regRequestStudentForm.getNome(),
            										regRequestStudentForm.getCognome(),
            										regRequestStudentForm.getMatricola(),
            										regRequestStudentForm.getEmail(),
            										regRequestStudentForm.getPassword()) == true) {
            	messages.add("nome", new ActionMessage("richiesta.registrazione.studente.ok"));
            } else {
            	errors.add("nome", new ActionError("richiesta.registrazione.studente.no"));
            }
        } catch (Exception e) {
        	errors.add("nome", new ActionError("general.error"));
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

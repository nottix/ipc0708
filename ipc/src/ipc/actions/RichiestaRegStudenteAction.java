package ipc.actions;

import ipc.control.LoginController;
import ipc.forms.RichiestaRegStudenteForm;

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
        	RichiestaRegStudenteForm aRegisterRequestStudentForm = (RichiestaRegStudenteForm) form;
            LoginController loginController = new LoginController();
            Hashtable<String, String> ht = new Hashtable<String, String>();
            ht.put("nome", aRegisterRequestStudentForm.getNome());
            ht.put("cognome", aRegisterRequestStudentForm.getCognome());
            ht.put("matricola", aRegisterRequestStudentForm.getMatricola());
            ht.put("password", aRegisterRequestStudentForm.getPassword());
            ht.put("email", aRegisterRequestStudentForm.getEmail());
            ht.put("status", "pendent");
            ht.put("tipologia", "studente");
            System.out.println("Richiesta");
            if(loginController.richiestaRegStudente(ht) == true) {
            	messages.add("nome", new ActionMessage("richiesta.registrazione.studente.ok"));
                System.out.println("Richiesta fatta");	
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

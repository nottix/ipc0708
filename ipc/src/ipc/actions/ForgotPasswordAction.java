package ipc.actions;

import ipc.control.LoginController;
import ipc.forms.ForgotPasswordForm;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @version 	1.0
 * @author
 */
public class ForgotPasswordAction extends Action {

    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form, 
    							 HttpServletRequest request, 
    							 HttpServletResponse response)
            					 throws Exception {
        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value

        try {
        	ForgotPasswordForm forgotPasswordForm = (ForgotPasswordForm)form;
            LoginController loginController = new LoginController();
            Hashtable<String, String> data = new Hashtable<String, String>();
            data.put("tipologia", "studente");
            data.put("email",  forgotPasswordForm.getEmail());
            data.put("status", "ripristino");
            loginController.richiestaNuovaPasswordStudente(data);
        } catch (Exception e) {
            errors.add("name", new ActionError("user.not.exists"));
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            forward = mapping.findForward("error");
        } else {
            forward = mapping.findForward("success");
        }
        return forward;
    }
}

package ipc.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.opensymphony.xwork2.ActionContext;
import ipc.forms.*;
import ipc.control.*;
import java.util.*;

/**
 * @version 	1.0
 * @author
 */
public class LoginAction extends Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value

        Map session;
        LoginForm loginForm = (LoginForm)form;
        LoginController loginController = new LoginController();
        String tipologia = "";
        
        try {

            if((tipologia=loginController.login(loginForm.getEmail(), loginForm.getPassword()))!=null) {
            	System.out.println("ok");
            	System.out.println(loginForm.getEmail() + " logged as "+tipologia);
            	
            	//session = ActionContext.getContext().getSession();
                //session.put("logged-in","true");
            }
            else {
            	System.out.println("ook");
            	errors.add("email", new ActionError("user.not.exists"));
            }

        } catch (Exception e) {

            // Report the error using the appropriate name and ID.
            errors.add("email", new ActionError("user.not.exists"));

        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.

        if (!errors.isEmpty()) {
            saveErrors(request, errors);

            // Forward control to the appropriate 'failure' URI (change name as desired)
            forward = mapping.findForward("error");

        } 
        else {
            // Forward control to the appropriate 'success' URI (change name as desired)
            if(!tipologia.equals("") && tipologia!=null)
            	forward = mapping.findForward(tipologia);
            else
            	forward = mapping.findForward("error");

        }

        // Finish with
        return (forward);

    }
    
    public String logout() throws Exception {

    	Map session = ActionContext.getContext().getSession();
    	session.remove("logged-in");
    	return "success";
    }
}

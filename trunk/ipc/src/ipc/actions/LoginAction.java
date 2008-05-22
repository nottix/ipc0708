package ipc.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ipc.forms.LoginForm;
import ipc.control.LoginController;

/**
 * @version 	1.0
 * @author
 */
public class LoginAction extends Action {
    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					 throws Exception {
    	ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        LoginForm loginForm = (LoginForm)form;
        LoginController loginController = new LoginController();
        String tipologia = "";
        try {
        	HttpSession session = request.getSession(false);
        	if(session != null) {
        		System.err.println("Sessione esistente");
        		tipologia = (String) session.getAttribute("tipologia");
        		System.err.println("tipologia " + tipologia);
        		if(tipologia!=null && !tipologia.equals("")) {
                	forward = mapping.findForward(tipologia);
                	System.out.println("forward " + tipologia);
                	return forward;
        		} else {
        			session.invalidate();
        		}
        	}
        	loginController.login(loginForm.getEmail(), loginForm.getPassword());
        	tipologia=loginController.getTipologia();
        	session = request.getSession(true);
        	session.setAttribute("email", loginForm.getEmail());
        	session.setAttribute("tipologia", tipologia);
        	if(tipologia.equals("professore")) {
        		if(loginController.isDirettore(loginForm.getEmail()) == true)
        			session.setAttribute("isDirettore", "true");
        		if(loginController.isTitolare(loginForm.getEmail()) == true)
        			session.setAttribute("isTitolare", "true");
        		if(loginController.isGestore(loginForm.getEmail()) == true)
        			session.setAttribute("isGestore", "true");
        		if(loginController.isCollaboratore(loginForm.getEmail()) == true)
        			session.setAttribute("isCollaboratore", "true");
        	}
        } catch (Exception e) {
            errors.add("email", new ActionError(e.getMessage()));
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            forward = mapping.findForward("error");
        } else {
            if(!tipologia.equals("") && tipologia!=null) {
            	forward = mapping.findForward(tipologia);
            	System.out.println("forward " + tipologia);
            }
            else
            	forward = mapping.findForward("error");
        }
        return forward;
    }
}

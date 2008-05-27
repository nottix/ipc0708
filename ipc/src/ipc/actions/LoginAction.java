package ipc.actions;

import ipc.forms.LoginForm;
import ipc.control.LoginController;

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
public class LoginAction extends Action {
    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					 throws Exception {
    	ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        LoginForm loginForm = (LoginForm)form;
        LoginController loginController = new LoginController();
        String tipologia = "";
        try {
        	HttpSession session = request.getSession(false);
        	if(session != null) {
        		System.err.println("Sessione esistente");
        		tipologia = (String) session.getAttribute("tipologia");
        		if(tipologia != null && tipologia.length() > 0) {
                	forward = mapping.findForward(tipologia);
                	System.out.println("forward " + tipologia);
                	return forward;
        		} else {
        			System.out.println("Sessione Invalidata");
        			session.invalidate();
        		}
        	}
        	if(loginController.login(loginForm.getEmail(), loginForm.getPassword()) == false) {
        		errors.add("tipologia", new ActionError("tipologia.error"));
        		System.out.println("tipologia del menga");
        	}
        	tipologia = loginController.getTipologia();
        	if(tipologia == null) {
        		errors.add("tipologia", new ActionError("tipologia.error"));
        	} else {
        		System.out.println("tipologia " + tipologia);
        		messages.add("tipologia", new ActionMessage("tipologia.ok"));
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
        	}
        } catch (Exception e) {
            errors.add("email", new ActionError("generic.error"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            forward = mapping.findForward("error");
        } else if(!messages.isEmpty()) {
        	saveMessages(request, messages);
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

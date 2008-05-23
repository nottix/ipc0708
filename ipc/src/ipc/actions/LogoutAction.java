package ipc.actions;

import javax.servlet.http.HttpSession;
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
public class LogoutAction extends Action {
	
    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        try {
        	HttpSession session = request.getSession(false);
        	if(session != null) {
        		if(session.getAttribute("email") != null)
        			session.removeAttribute("email");
        		if(session.getAttribute("tipologia") != null)
        			session.removeAttribute("tipologia");
        		if(session.getAttribute("isDirettore") != null)
        			session.removeAttribute("isDirettore");
        		if(session.getAttribute("isTitolare") != null)
        			session.removeAttribute("isTitolare");
        		if(session.getAttribute("isGestore") != null)
        			session.removeAttribute("isGestore");
        		session.invalidate();
        	} else
        		errors.add("email", new ActionError("email.session.error"));
        } catch (Exception e) {
            errors.add("email", new ActionError("generic.error"));
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }
        return mapping.findForward("success");
    }
}

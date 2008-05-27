package ipc.actions;

import java.util.Enumeration;

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
public class GestioneEsameAction extends Action {

    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form,
    							 HttpServletRequest request, 
    							 HttpServletResponse response)
    							throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        try {
        	if (isCancelled(request)) {
        		return mapping.findForward("main");
        	}
        	Enumeration en = request.getParameterNames();
        	if(en.hasMoreElements() == false) {
        		errors.add("nome", new ActionError("radio.button.error"));
        	} else {
        		while(en.hasMoreElements()) {
        			String name = (String)en.nextElement();
        			if(name.equals("radio")) {
        				System.out.println("request: "+request.getParameter(name));
        				String acronimo = request.getParameter(name);
        				request.setAttribute("acronimo", acronimo);
        				request.getSession().setAttribute("acronimo", acronimo);
        				System.out.println("forward: "+mapping.findForward("success").getPath()+"?acronimo="+request.getParameter(name));
        			}
        		}
        	}
        } catch (Exception e) {
            errors.add("name", new ActionError("generic.error"));
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

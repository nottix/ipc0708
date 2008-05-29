package ipc.actions;

import ipc.forms.GestioneCorsoForm;

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
public class GestioneCorsoAction extends Action {
	private String acronimo = null;
	
	public String getAcronimo() {
		return this.acronimo;
	}

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
        	System.out.println("en " + en);
        	if(en.hasMoreElements() == false) {
        		errors.add("name", new ActionError("radio.button.error"));
        	} else {
        		while(en.hasMoreElements()) {
        			String name = (String)en.nextElement();
        			System.out.println("request: "+request.getParameter(name));
        			if(name.equals("radio")) {
        				System.out.println("request: "+request.getParameter(name));
        				GestioneCorsoForm gestForm = (GestioneCorsoForm)form;
        				gestForm.setAcronimo(request.getParameter(name));
        				acronimo = request.getParameter(name);
        				if(acronimo == null) {
        					errors.add("name", new ActionError("radio.button.error"));
        				}
        				request.getSession().setAttribute("acronimo", acronimo);
        				request.getSession().setAttribute("old_acronimo", acronimo);
        			}
        		}
        	}
        } catch (Exception e) {
            errors.add("name", new ActionError("generic.error"));
        }
        if (!errors.isEmpty()) {
        	System.out.println("Ci sono errori");
            saveErrors(request, errors);
            forward = mapping.findForward("error");
        } else {
            forward = mapping.findForward("success");
        }
        return forward;
    }
}

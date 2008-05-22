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
import ipc.forms.*;

/**
 * @version 	1.0
 * @author
 */
public class GestioneCorsoAction extends Action

{
	private String acronimo = null;
	
	public String getAcronimo() {
		return this.acronimo;
	}

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value

        try {
        	if (isCancelled(request)) {
        		return mapping.findForward("main");
        	}
        	Enumeration en = request.getParameterNames();
        	while(en.hasMoreElements()) {
        		String name = (String)en.nextElement();
        		if(name.equals("radio")) {
        			System.out.println("request: "+request.getParameter(name));
        			GestioneCorsoForm gestForm = (GestioneCorsoForm)form;
        			gestForm.setAcronimo(request.getParameter(name));
        			acronimo = request.getParameter(name);
        			request.getSession().setAttribute("acronimo", acronimo);
        			//System.out.println("forward: "+mapping.findForward("success").getPath()+"?acronimo="+request.getParameter(name));
                	//return new ActionForward(mapping.findForward("success").getPath()+"?acronimo="+request.getParameter(name), false);
        		}
        	}
            

        } catch (Exception e) {

            // Report the error using the appropriate name and ID.
            errors.add("name", new ActionError("id"));

        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.

        if (!errors.isEmpty()) {
            saveErrors(request, errors);

            // Forward control to the appropriate 'failure' URI (change name as desired)
            //	forward = mapping.findForward(non riuscito");

        } else {

            // Forward control to the appropriate 'success' URI (change name as desired)
            forward = mapping.findForward("success");

        }

        // Finish with
        return (forward);

    }
}

package ipc.actions;

import java.util.Enumeration;

import ipc.forms.GestioneCorsoForm;

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
public class CreazioneEsameAction extends Action

{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value

        try {
        	System.out.println("ok");
        	Enumeration en = request.getParameterNames();
        	while(en.hasMoreElements()) {
        		String name = (String)en.nextElement();
        		System.out.println("name: "+name+", value: "+request.getParameter(name));
        		//request.setAttribute("acronimo", request.getParameter(name));
        		CreazioneEsameForm cForm = ((CreazioneEsameForm)form);
        		cForm.setAcronimo(request.getParameter(name));
        	}
        	//System.out.println("forward: "+mapping.findForward("success").getPath()+"?acronimo="+((GestioneCorsoForm)form).getAcronimo());
        	//return new ActionForward(mapping.findForward("success").getPath()+"?acronimo="+((GestioneCorsoForm)form).getAcronimo(), false);

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
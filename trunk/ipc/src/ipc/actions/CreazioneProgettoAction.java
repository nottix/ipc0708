package ipc.actions;

import ipc.forms.CreazioneProgettoForm;

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
public class CreazioneProgettoAction extends Action {

    public ActionForward execute(ActionMapping mapping,
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        try {
        	Enumeration en = request.getParameterNames();
        	while(en.hasMoreElements()) {
        		String name = (String)en.nextElement();
        		System.out.println("name: "+name+", value: "+request.getParameter(name));
        		CreazioneProgettoForm cForm = ((CreazioneProgettoForm)form);
        		cForm.setAcronimo(request.getParameter(name));
        	}
        } catch (Exception e) {
            errors.add("name", new ActionError("generic.error"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        } else {
            forward = mapping.findForward("success");
        }
        return (forward);
    }
}

package ipc.actions;

import ipc.forms.CreazioneEsameForm;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CreazioneEsameAction extends Action {

    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
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
        } catch (Exception e) {
            errors.add("name", new ActionError("id"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        } else {
        	forward = mapping.findForward("success");
        }

        // Finish with
        return (forward);

    }
}

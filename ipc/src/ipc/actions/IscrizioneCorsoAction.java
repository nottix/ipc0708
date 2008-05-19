package ipc.actions;

import ipc.control.GestioneStudenteController;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class IscrizioneCorsoAction extends Action {

    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        GestioneStudenteController control = new GestioneStudenteController();

        try {
        	if (isCancelled(request)) {
        		return mapping.findForward("main");
        	}
        	HttpSession session = request.getSession(false);
        	String idStudente = null;
        	if(session != null) {
        		idStudente = (String)session.getAttribute("email");
        	}
        	Hashtable<String, Object> data = new Hashtable<String, Object>();
        	data.put("idStudente", idStudente);
        	data.put("dataIscrizione", new Date());
        	
        	Enumeration en = request.getParameterNames();
        	while(en.hasMoreElements()) {
        		String name = (String)en.nextElement();
        		if(name.equals("radio")) {
        			System.out.println("request: "+request.getParameter(name));
        			String acronimo = request.getParameter(name);
        			data.put("acronimo", acronimo);
        			if(control.iscrizioneCorso(data))
        				errors.add("name", new ActionError("iscrizioneCorso.created"));
        			else
        				errors.add("name", new ActionError("iscrizioneCorso.ncreated"));
        		}
        	}
        }
        catch (Exception e) {

            // Report the error using the appropriate name and ID.
            errors.add("name", new ActionError("iscrizioneCorso.ncreated"));

        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.

        if (!errors.isEmpty()) {
            saveErrors(request, errors);

            // Forward control to the appropriate 'failure' URI (change name as desired)
            forward = mapping.findForward("success");

        }

        // Finish with
        return (forward);

    }
}

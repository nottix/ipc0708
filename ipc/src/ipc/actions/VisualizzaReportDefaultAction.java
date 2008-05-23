package ipc.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ipc.entity.*;
import ipc.control.*;
import java.util.*;

/**
 * @version 	1.0
 * @author
 */
public class VisualizzaReportDefaultAction extends Action

{
	
	private List<Account> elencoAccountStudenti;
	
	public List<Account> getElencoAccountStudenti() {
		return this.elencoAccountStudenti;
	}

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value
        GestioneQueryController control = new GestioneQueryController();

        try {

        	String acronimo="";
        	Enumeration en = request.getParameterNames();
        	String name;
        	List<String> columns = new LinkedList<String>();
        	while(en.hasMoreElements()) {
        		name = (String)en.nextElement();
        		System.out.println("name: "+name);
        		if(name.equals("radio")) {
        			acronimo = request.getParameter(name);
        			System.out.println("name: "+acronimo);
        			
        			elencoAccountStudenti = control.queryDefault(acronimo);
                    System.out.println("size results: "+elencoAccountStudenti.size());
                    request.setAttribute("elencoAccountStudenti", elencoAccountStudenti);
        		}
        		else {
        			columns.add(name);
        			System.out.println("column: "+name);
        		}
        	}
        	elencoAccountStudenti = control.ordinamentoReport(columns, elencoAccountStudenti);
        	request.setAttribute("elencoAccountStudenti", elencoAccountStudenti);
        } catch (Exception e) {

            // Report the error using the appropriate name and ID.
            errors.add("name", new ActionError("id"));

        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.

        if (!errors.isEmpty()) {
            saveErrors(request, errors);

            // Forward control to the appropriate 'failure' URI (change name as desired)
            forward = mapping.findForward("error");

        } else {

            // Forward control to the appropriate 'success' URI (change name as desired)
            forward = mapping.findForward("success");

        }

        // Finish with
        return (forward);

    }
}

package ipc.actions;

import ipc.control.GestioneQueryController;
import ipc.entity.Account;

import java.util.List;
import java.util.LinkedList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class VisualizzaReportDefaultAction extends Action {
	
	private List<Account> elencoAccountStudenti;
	
	public List<Account> getElencoAccountStudenti() {
		return this.elencoAccountStudenti;
	}

    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form, 
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ActionMessages messages = new ActionMessages();
        GestioneQueryController control = new GestioneQueryController();
        try {
        	String acronimo="";
        	String name;
        	Enumeration en = request.getParameterNames();
        	if(en.hasMoreElements() == false) {
        		errors.add("name", new ActionError("radio.button.error"));
        	} else {
        		List<String> columns = new LinkedList<String>();
        		while(en.hasMoreElements()) {
        			name = (String)en.nextElement();
        			System.out.println("name: "+name);
        			if(name.equals("radio")) {
        				acronimo = request.getParameter(name);
        				System.out.println("name: "+acronimo);

        				elencoAccountStudenti = control.queryDefault(acronimo);
        				if(elencoAccountStudenti == null) {
        					errors.add("nome", new ActionError("elenco.studenti.no"));
        				} else {
        					messages.add("nome", new ActionMessage("elenco.studenti.ok"));
        					System.out.println("size results: "+elencoAccountStudenti.size());
        					request.setAttribute("elencoAccountStudenti", elencoAccountStudenti);
        				}
        			} else {
        				columns.add(name);
        				System.out.println("column: "+name);
        			}
        		}
        		elencoAccountStudenti = control.ordinamentoReport(columns, elencoAccountStudenti);
        		if(elencoAccountStudenti == null) {
        			errors.add("nome", new ActionError("ordinamento.no"));
        		} else {
        			messages.add("nome", new ActionMessage("ordinamento.ok"));
        			request.setAttribute("elencoAccountStudenti", elencoAccountStudenti);
        		}
        	}
        } catch (Exception e) {
            errors.add("name", new ActionError("generic.error"));
        }
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            forward = mapping.findForward("error");
        } else {
        	saveMessages(request, messages);
            forward = mapping.findForward("success");
        }
        return forward;
    }
}

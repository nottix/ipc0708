package ipc.actions;

import ipc.control.CreazioneProfessoreController;
import ipc.forms.CreazioneProfessoreForm;

import java.util.Hashtable;

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
public class CreazioneProfessoreAction extends Action {
    public ActionForward execute(ActionMapping mapping, 
    							 ActionForm form,
    							 HttpServletRequest request,
    							 HttpServletResponse response)
            					throws Exception {
        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        CreazioneProfessoreController creazioneProfessoreController = new CreazioneProfessoreController();
        CreazioneProfessoreForm creazioneProfessoreForm = (CreazioneProfessoreForm)form;
        
        try {
        	System.out.println("Controller1");
        	Hashtable<String, Object> hash = new Hashtable<String, Object>();
        	hash.put("nome", creazioneProfessoreForm.getNome());
        	hash.put("cognome", creazioneProfessoreForm.getCognome());
        	hash.put("email", creazioneProfessoreForm.getEmail());
        	hash.put("password", creazioneProfessoreForm.getPassword());
        	System.out.println("Controller2: "+creazioneProfessoreForm.getIsDirettore()+ ", "+ creazioneProfessoreForm.getIsGestore());
        	hash.put("isDirettore", creazioneProfessoreForm.getIsDirettore()!=null ? Boolean.TRUE : Boolean.FALSE);
        	hash.put("isGestore", creazioneProfessoreForm.getIsGestore()!=null ? Boolean.TRUE : Boolean.FALSE);
        	System.out.println("Controller");
        	if(!creazioneProfessoreController.creazioneProfessore(hash))
        		errors.add("email", new ActionError("user.already.exists"));
        } catch (Exception e) {
            errors.add("password", new ActionError("passwordmatch.error"));
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);

            // Forward control to the appropriate 'failure' URI (change name as desired)
            //	forward = mapping.findForward(non riuscito");
            forward = mapping.findForward("error");
        } else {
            forward = mapping.findForward("success");
        }
        return forward;
    }
}
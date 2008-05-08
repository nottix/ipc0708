package ipc.actions;

import java.util.Hashtable;
import ipc.control.GestioneStudenteController;
import ipc.control.LoginController;
import ipc.forms.RichiestaRegStudenteForm;

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
public class RichiestaRegStudenteAction extends Action

{

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward(); // return value

        try {
        	RichiestaRegStudenteForm aRegisterRequestStudentForm = (RichiestaRegStudenteForm) form;
            GestioneStudenteController gestioneStudenteController = new GestioneStudenteController();
            Hashtable<String, String> ht = new Hashtable<String, String>();
            ht.put("nome", aRegisterRequestStudentForm.getNome());
            ht.put("cognome", aRegisterRequestStudentForm.getCognome());
            ht.put("matricola", aRegisterRequestStudentForm.getMatricola());
            ht.put("password", LoginController.convertToMD5(aRegisterRequestStudentForm.getPassword()));
            ht.put("email", aRegisterRequestStudentForm.getEmail());
            ht.put("status", "pendent");
            ht.put("tipologia", "studente");
            Boolean ret = gestioneStudenteController.richiestaRegStudente(ht);
            if(ret == false) {
            	errors.add("nome", new ActionError("Utente gia' esistente o dati errati"));
            } else {
            	
            }
            
        } catch (Exception e) {

            // Report the error using the appropriate name and ID.
            errors.add("name", new ActionError("id"));

        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            forward = mapping.findForward("error");
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

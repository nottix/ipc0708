package ipc.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 4 fields on this form:
 * <ul>
 * <li>email - [your comment here]
 * <li>dataEsame - [your comment here]
 * <li>dataPrenotazione - [your comment here]
 * <li>submit - [your comment here]
 * </ul>
 *
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class ConfermaPrenotazioneEsameForm extends ActionForm {

	private static final long serialVersionUID = 4L;

	private String email = null;

    private String dataEsame = null;

    private String dataPrenotazione = null;

    private String submit = null;

    /**
     * Get email
     * @return String
     */
    public String getEmail() {
    	return email;
    }

    /**
     * Set email
     * @param <code>String</code>
     */
    public void setEmail(String e) {
    	this.email = e;
    }

    /**
     * Get dataEsame
     * @return String
     */
    public String getDataEsame() {
    	return dataEsame;
    }

    /**
     * Set dataEsame
     * @param <code>String</code>
     */
    public void setDataEsame(String d) {
    	this.dataEsame = d;
    }

    /**
     * Get dataPrenotazione
     * @return String
     */
    public String getDataPrenotazione() {
    	return dataPrenotazione;
    }

    /**
     * Set dataPrenotazione
     * @param <code>String</code>
     */
    public void setDataPrenotazione(String d) {
    	this.dataPrenotazione = d;
    }

    /**
     * Get submit
     * @return String
     */
    public String getSubmit() {
    	return submit;
    }

    /**
     * Set submit
     * @param <code>String</code>
     */
    public void setSubmit(String s) {
    	this.submit = s;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	// Reset values are provided as samples only. Change as appropriate.
    	email = null;
    	dataEsame = null;
    	dataPrenotazione = null;
    	submit = null;
    }

    public ActionErrors validate(ActionMapping mapping,
    							 HttpServletRequest request) {

    	ActionErrors errors = new ActionErrors();
    	if((dataEsame != null) && (dataEsame.length() == 0))
    		errors.add("dataEsame", new ActionError("data.esame.error"));
    	if((dataPrenotazione != null) && (dataPrenotazione.length() == 0))
    		errors.add("dataPrenotazione", new ActionError("data.prenotazione.error"));
    	if((email != null) && (email.length() == 0))
    		errors.add("email", new ActionError("email.error"));
		//else if(!Utils.check_email(email))
		//	errors.add("email", new ActionError("email.malformed"));
    	return errors;
    }
}

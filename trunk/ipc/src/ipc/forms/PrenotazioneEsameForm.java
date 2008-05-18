package ipc.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 4 fields on this form:
 * <ul>
 * <li>dataInizioPeriodoPrenotazione - [your comment here]
 * <li>dataFinePeriodoPrenotazione - [your comment here]
 * <li>dataEsame - [your comment here]
 * <li>auleEsame - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class PrenotazioneEsameForm extends ActionForm

{

    private String dataInizioPeriodoPrenotazione = null;

    private String dataFinePeriodoPrenotazione = null;

    private String dataEsame = null;

    private String auleEsame = null;

    /**
     * Get dataInizioPeriodoPrenotazione
     * @return String
     */
    public String getDataInizioPeriodoPrenotazione() {
	return dataInizioPeriodoPrenotazione;
    }

    /**
     * Set dataInizioPeriodoPrenotazione
     * @param <code>String</code>
     */
    public void setDataInizioPeriodoPrenotazione(String d) {
	this.dataInizioPeriodoPrenotazione = d;
    }

    /**
     * Get dataFinePeriodoPrenotazione
     * @return String
     */
    public String getDataFinePeriodoPrenotazione() {
	return dataFinePeriodoPrenotazione;
    }

    /**
     * Set dataFinePeriodoPrenotazione
     * @param <code>String</code>
     */
    public void setDataFinePeriodoPrenotazione(String d) {
	this.dataFinePeriodoPrenotazione = d;
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
     * Get auleEsame
     * @return String
     */
    public String getAuleEsame() {
	return auleEsame;
    }

    /**
     * Set auleEsame
     * @param <code>String</code>
     */
    public void setAuleEsame(String a) {
	this.auleEsame = a;
    }

    public void reset(ActionMapping mapping, HttpServletRequest request) {

	// Reset values are provided as samples only. Change as appropriate.

	dataInizioPeriodoPrenotazione = null;
	dataFinePeriodoPrenotazione = null;
	dataEsame = null;
	auleEsame = null;

    }

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {

	ActionErrors errors = new ActionErrors();
	// Validate the fields in your form, adding
	// adding each error to this.errors as found, e.g.

	// if ((field == null) || (field.length() == 0)) {
	//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
	// }
	return errors;

    }
}

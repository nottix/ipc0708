package ipc.forms;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
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
public class PrenotazioneEsameForm extends ActionForm {
	private static final long serialVersionUID = 21L;
	
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
		dataInizioPeriodoPrenotazione = null;
		dataFinePeriodoPrenotazione = null;
		dataEsame = null;
		auleEsame = null;
    }

    public ActionErrors validate(ActionMapping mapping,
    							 HttpServletRequest request) {

    	ActionErrors errors = new ActionErrors();
    	if((dataInizioPeriodoPrenotazione == null) || (dataInizioPeriodoPrenotazione.length() == 0))
    		errors.add("dataInizioPeriodoPrenotazione", new ActionError("data.inizio.periodo.prenotazione.error"));
    	if((dataFinePeriodoPrenotazione == null) || (dataFinePeriodoPrenotazione.length() == 0))
    		errors.add("dataFinePeriodoPrenotazione", new ActionError("data.fine.periodo.prenotazione.error"));
    	if((dataEsame == null ) || (dataEsame.length() == 0))
    		errors.add("dataEsame", new ActionError("data.esame.error"));
    	return errors;

    }
}

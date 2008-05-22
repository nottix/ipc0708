package ipc.forms;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


/**
 * Form bean for a Struts application.
 * Users may access 5 fields on this form:
 * <ul>
 * <li>nome - [your comment here]
 * <li>acronimo - [your comment here]
 * <li>dataApertura - [your comment here]
 * <li>dataChiusura - [your comment here]
 * <li><bean:write name="user" property="email" /> - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class CreazioneCorsoForm extends ActionForm {
	
	private static final long serialVersionUID = 11L;
	private String nome = null;
	private String acronimo = null;
	private String descrizione = null;
	private String dataApertura = null;
	private String dataChiusura = null;

	/**
	 * Get nome
	 * @return String
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Set nome
	 * @param <code>String</code>
	 */
	public void setNome(String n) {
		this.nome = n;
	}

	/**
	 * Get acronimo
	 * @return String
	 */
	public String getAcronimo() {
		return acronimo;
	}

	/**
	 * Set acronimo
	 * @param <code>String</code>
	 */
	public void setAcronimo(String a) {
		this.acronimo = a;
	}
	
	/**
	 * Get descrizione
	 * @return String
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Set descrizione
	 * @param <code>String</code>
	 */
	public void setDescrizione(String a) {
		this.descrizione = a;
	}

	/**
	 * Get dataApertura
	 * @return String
	 */
	public String getDataApertura() {
		return dataApertura;
	}

	/**
	 * Set dataApertura
	 * @param <code>String</code>
	 */
	public void setDataApertura(String d) {
		this.dataApertura = d;
	}

	/**
	 * Get dataChiusura
	 * @return String
	 */
	public String getDataChiusura() {
		return dataChiusura;
	}

	/**
	 * Set dataChiusura
	 * @param <code>String</code>
	 */
	public void setDataChiusura(String d) {
		this.dataChiusura = d;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)  {
		nome = null;
		acronimo = null;
		dataApertura = null;
		dataChiusura = null;
	}

	public ActionErrors validate(ActionMapping mapping, 
								 HttpServletRequest request)  {

		ActionErrors errors = new ActionErrors();
		if((nome != null) && (nome.length() == 0))
			errors.add("nome", new ActionError("nome.error"));
		if((acronimo != null) && (acronimo.length() == 0))
			errors.add("acronimo", new ActionError("acronimo.error"));
		if((dataApertura != null) && (dataApertura.length() == 0))
			errors.add("dataApertura", new ActionError("data.apertura.error"));
		else if(!this.check_data(dataApertura))
			errors.add("dataApertura", new ActionError("data.invalid.format"));
		if((dataChiusura != null) && (dataChiusura.length() == 0))
			errors.add("dataChiusura", new ActionError("data.chiusura.error"));
		else if(!this.check_data(dataChiusura))
			errors.add("dataChiusura", new ActionError("data.invalid.format"));
//		if(dataApertura != null && dataChiusura != null)
//			if(cmpDates(dataApertura, dataChiusura))
//				errors.add("dataApertura", new ActionError("data.apartura.chiusura.invalid"));
		return errors;
	}
	
	 public boolean check_data(String data) {
//			boolean test1 = Pattern.compile("[0-9]{1,2}/[0-9]{1,2}/20[0-9]{2}").matcher(data).matches();
//			String []dates = data.split("/");
//			if(Integer.valueOf(dates[0]) < 1 || Integer.valueOf(dates[0]) > 31)
//				return false;
//			if(Integer.valueOf(dates[1]) < 1 || Integer.valueOf(dates[1]) > 12)
//				return false;
//			if(Integer.valueOf(dates[2]) < 2000 || Integer.valueOf(dates[2]) > 2099)
//				return false;
//			return test1;
		 return true;
	 }
	
}
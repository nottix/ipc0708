package ipc.forms;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/*Aggiungere il controllo se esiste gia' una email sul db*/
public class RegistrazioneStudenteForm extends ActionForm {
	private static final long serialVersionUID = 2L;
	private String nome;
	private String cognome;
	private String matricola;
	private String email;
	private String password;
	private String passwordCheck;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return this.nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCognome() {
		return this.cognome;
	}
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	public String getMatricola() {
		return this.matricola;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return this.email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
	public String getPasswordCheck() {
		return this.passwordCheck;
	}
	public ActionErrors validate(ActionMapping mapping,
		    					 HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		boolean pwdchk = true;
		if((nome == null) || (nome.length() == 0))
			errors.add("nome", new ActionError("Il campo nome non e' definito"));
		if((cognome == null) || (cognome.length() == 0))
			errors.add("cognome", new ActionError("Il campo cognome non e' definito"));
		if((matricola == null) || (matricola.length() == 0))
			errors.add("matricola", new ActionError("Il campo matricola non e' definito"));
		if((password == null) || (password.length() == 0)) {
			pwdchk = false;
			errors.add("password", new ActionError("Il campo password non e' definito"));
		}
		if((passwordCheck == null) || (passwordCheck.length() == 0)) {
			pwdchk = false;
			errors.add("passwordCheck", new ActionError("Il campo passwordCheck non e' definito"));
		}
		if(pwdchk && !password.equals(passwordCheck))
			errors.add("password", new ActionError("Le due password non coincidono"));
		if((email == null) || (email.length() == 0))
			errors.add("email", new ActionError("Il campo email non e' definito"));
		else if(check_email(email) == false)
			errors.add("email", new ActionError("Il campo email contiene caratteri non validi"));
		return errors;
	}
	
	/*If it returns false we have an invalid email!*/
	private boolean check_email(String email) {
		boolean test1 = Pattern.compile("[a-zA-Z0-9_]+[.[a-zA-Z0-9]+]*@[a-zA-Z0-9_]+[.[a-zA-Z]+]+").matcher(email).matches();
		boolean test2 = Pattern.compile("^\\S+@\\S+$").matcher(email).matches();
		boolean test3 = Pattern.compile(".+@.+\\.[a-z]+").matcher(email).matches();
		boolean test4 = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(email).matches();
		return test1 && test2 && test3 && test4;
	}
}

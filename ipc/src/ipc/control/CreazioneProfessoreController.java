/**
 * 
 */
package ipc.control;

import ipc.db.AccountDAO;
import ipc.db.DAOFactory;
import ipc.entity.Account;

public class CreazioneProfessoreController {

	public Boolean creazioneProfessore(String email, String nome,
									   String cognome, String password,
									   Boolean isDirettore, Boolean isGestore,
									   String noteProf) {
		try {
			DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
			AccountDAO accountDao = factory.getAccountDAO();
			
			if(accountDao.read(email) == null) {
				Account professore = new Account();
				professore.setEmail(email);
				professore.setCognome(cognome);
				professore.setNome(nome);
				professore.setPassword(Account.convertToMD5(password));
				professore.setStatus("attivo");
				professore.setTipologia("professore");
				if(noteProf != null)
					professore.setNoteProf(noteProf);
				if(isDirettore != null)
					professore.setIsDirettore(isDirettore);
				if(isGestore != null)
					professore.setIsGestore(isGestore);
				return accountDao.create(professore);
			}
			//Invio email informativa
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}

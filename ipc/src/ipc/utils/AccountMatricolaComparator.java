package ipc.utils;

import java.util.Comparator;

import ipc.entity.Account;
/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class AccountMatricolaComparator implements Comparator {

	public int compare(Object o1, Object o2) {
        String matricola1 = ((Account)o1).getMatricola();
        String matricola2 = ((Account)o2).getMatricola();
        return matricola1.compareTo(matricola2);
	}
}
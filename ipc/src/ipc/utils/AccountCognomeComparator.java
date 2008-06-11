package ipc.utils;

import java.util.Comparator;

import ipc.entity.Account;
/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class AccountCognomeComparator implements Comparator {

	public int compare(Object o1, Object o2) {
        String cognome1 = ((Account)o1).getCognome();
        String cognome2 = ((Account)o2).getCognome();
        return cognome1.compareTo(cognome2);
	}
}
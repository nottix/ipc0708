/**
 * 
 */
package ipc.utils;

import java.util.Comparator;
import ipc.entity.*;
/**
 * @version 	1.0
 * @author		Laurenziello Vincenzo
 * @author 		Notargiacomo Simone
 * @author		Scenna Fabrizio
 */
public class AccountNomeComparator implements Comparator {

	public int compare(Object o1, Object o2) {
        String nome1 = ((Account)o1).getNome();
        String nome2 = ((Account)o2).getNome();
        return nome1.compareTo(nome2);
	}
}

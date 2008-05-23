package ipc.utils;

import ipc.entity.Account;

import java.util.Comparator;

public class AccountEmailComparator implements Comparator {

	public int compare(Object o1, Object o2) {
        String email1 = ((Account)o1).getEmail();
        String email2 = ((Account)o2).getEmail();
        return email1.compareTo(email2);
	}

}
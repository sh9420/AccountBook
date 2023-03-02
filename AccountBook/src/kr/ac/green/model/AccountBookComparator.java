package kr.ac.green.model;

import java.util.Comparator;

public class AccountBookComparator implements Comparator<AccountBook> {
	  @Override
	  public int compare(AccountBook a1, AccountBook a2) {
		  int result = 0;
		  result = a1.getAllDay().compareTo(a1.getAllDay());
		  return a1.getAllDay().compareTo(a2.getAllDay());
	  }
}

package com.addressbooksystem;

import java.util.Comparator;

public class SortPersonsByZip implements Comparator<Person>{

	@Override
	public int compare(Person person1, Person person2) {
		return person1.compareToByZip(person2);
	}

}

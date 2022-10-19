package com.addressbooksystem;

import java.util.Comparator;

public class SortPersonsByName implements Comparator<Person>{

	@Override
	public int compare(Person person1, Person person2) {
		return person1.compareToByName(person2);
	}

}

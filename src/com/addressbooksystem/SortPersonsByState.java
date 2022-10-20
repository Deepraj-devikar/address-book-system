package com.addressbooksystem;

import java.util.Comparator;

public class SortPersonsByState implements Comparator<Person>{

	@Override
	public int compare(Person person1, Person person2) {
		return person1.compareToByState(person2);
	}

}

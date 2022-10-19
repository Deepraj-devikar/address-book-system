package com.addressbooksystem;

import java.util.ArrayList;
import java.util.Collections;

public class AddressBook {
	public ArrayList<Person> contacts = new ArrayList<Person>();
	
	/**
	 * add contact to contacts of address book with first name and last name
	 * and return that contact which type is person to set further information
	 * 
	 * @param firstName
	 * @param lastName
	 * @return contact
	 * @throws DuplicateContactException
	 */
	public Person addContact(String firstName, String lastName) throws DuplicateContactException{
		Person person = new Person(firstName, lastName);
		if(! contacts.stream().anyMatch((tempPerson) -> person.equals(tempPerson))) {
			contacts.add(person);
			return person;
		} else {
			throw new DuplicateContactException(firstName, lastName);
		}
	}
	
	public void showContacts() {
		Collections.sort(contacts, new SortPersonsByName());
		contacts.forEach((contact) -> {
			System.out.println(contact);
		});
	}
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @return index if found otherwise -1
	 */
	public int indexOfContact(String firstName, String lastName) {
		for(int index = 0; index < contacts.size(); index++) {
			Person cantact = contacts.get(index);
			if (cantact.getFirstName().equals(firstName) && cantact.getLastName().equals(lastName))
				return index;
		}
		return -1;
	}
	
	/**
	 * get the contact with first name and last name and return it
	 * 
	 * @param firstName
	 * @param lastName
	 * @return contact
	 */
	public Person getContact(String firstName, String lastName) {
		int index = indexOfContact(firstName, lastName);
		if(index == -1) {
			return null;
		}
		return contacts.get(index);
	}
	
	/**
	 * delete contact with first name and last name if contact found
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public void deleteContact(String firstName, String lastName) {
		Person contact = getContact(firstName, lastName);
		if(contact != null) {
			contacts.remove(contact);
		}
	}
}

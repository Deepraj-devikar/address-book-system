package com.addressbooksystem;

import java.util.ArrayList;

public class AddressBook {
	public ArrayList<Person> contacts = new ArrayList<Person>();
	
	public void addContact(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
		contacts.add(new Person(firstName, lastName, address, city, state, zip, phoneNumber, email));
	}
	
	public void showContacts() {
		contacts.forEach((contact) -> {
			System.out.println("");
			System.out.println(contact.firstName+" "+contact.lastName+" : ");
			System.out.println(contact.address+", ");
			System.out.println(contact.city+", "+contact.state+" - "+contact.zip);
			System.out.println(contact.phoneNumber);
			System.out.println(contact.email);
			System.out.println("");
		});
	}

}

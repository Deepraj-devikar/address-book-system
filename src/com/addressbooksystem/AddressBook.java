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
	
	public int indexOfContact(String firstName, String lastName) {
		for(int index = 0; index < contacts.size(); index++) {
			Person cantact = contacts.get(index);
			if (cantact.getFirstName().equals(firstName) && cantact.getLastName().equals(lastName))
				return index;
		}
		return -1;
	}
	
	public void editContact(int index, String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
		Person contact = contacts.get(index);
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setAddress(address);
		contact.setCity(city);
		contact.setState(state);
		contact.setZip(zip);
		contact.setPhoneNumber(phoneNumber);
		contact.setEmail(email);
	}
	
	public void deleteContact(int index) {
		contacts.remove(index);
	}
}

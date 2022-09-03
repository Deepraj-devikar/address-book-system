package com.addressbooksystem;

import java.util.ArrayList;

public class AddressBook {
	public ArrayList<Person> contacts = new ArrayList<Person>();
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 * @param phoneNumber
	 * @param email
	 */
	public void addContact(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
		contacts.add(new Person(firstName, lastName, address, city, state, zip, phoneNumber, email));
	}
	
	public void showContacts() {
		contacts.forEach((contact) -> {
			System.out.println("");
			System.out.println(contact.getFirstName()+" "+contact.getLastName()+" : ");
			System.out.println(contact.getAddress()+", ");
			System.out.println(contact.getCity()+", "+contact.getState()+" - "+contact.getZip());
			System.out.println(contact.getPhoneNumber());
			System.out.println(contact.getEmail());
			System.out.println("");
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
	 * 
	 * @param index
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 * @param phoneNumber
	 * @param email
	 */
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
	
	/**
	 * 
	 * @param index
	 */
	public void deleteContact(int index) {
		contacts.remove(index);
	}
}

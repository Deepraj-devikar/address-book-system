package com.addressbooksystem;

import java.util.*;

public class AddressBookMain {
	// For taking inputs from console
	static Scanner scanner = new Scanner(System.in);
	
	// Contact details holding variables
	static String firstName, lastName, address, city, state, zip, phoneNumber, email;
	
	public static void readPersonDetails() {
		System.out.print("First Name : ");
		firstName = scanner.nextLine();
		System.out.print("Last Name : ");
		lastName = scanner.nextLine();
	}
	
	public static void readContactDetails() {
		System.out.print("Address : ");
		address = scanner.nextLine();
		System.out.print("City : ");
		city = scanner.nextLine();
		System.out.print("State : ");
		state = scanner.nextLine();
		System.out.print("Zip : ");
		zip = scanner.nextLine();
		System.out.print("Phone Number : ");
		phoneNumber = scanner.nextLine();
		System.out.print("Email : ");
		email = scanner.nextLine();
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		
		// For more than one address book according to address book name
		Dictionary<String, AddressBook> addressBooks = new Hashtable<String, AddressBook>();
		
		boolean isFirstAddressBook = true;
		while (true) {
			// Will add first address book then ask to add another address book or not
			if (!isFirstAddressBook) {
				System.out.print("Enter 1 if you want add one more address book otherwise Enter 0 : ");
				// Not want to add address book then will break loop
				// Want to add address book then scanner.nextInt() make skip scanner.nextLine()
				if(scanner.nextInt() == 0) break; else scanner.nextLine();
			}
			
			// Makes address book according to name
			System.out.print("Enter name for address book : ");
			String addressBookName = scanner.nextLine();
			addressBooks.put(addressBookName, new AddressBook());
			
			// Add contacts to address book 
			System.out.print("How many persons details you have to add : ");
			int numberOfPerson = scanner.nextInt();
			scanner.nextLine(); // Will skip by scanner.nextInt();
			System.out.println("Enter "+numberOfPerson+" person details: ");
			for (int number = 1; number <= numberOfPerson; number++) {
				System.out.println("");
				System.out.println("Person "+number+" : ");
				// Read contact details with person first name and last name and add that details to address book
				try {
					// add contact details to the address book 
					// if contact details with first name and last name is already found in address book 
					// then it will give DuplicateContactException
					readPersonDetails();
					Person addContact = addressBooks.get(addressBookName).addContact(firstName, lastName);
					readContactDetails();
					addContact.setAddress(address);
					addContact.setCity(city);
					addContact.setState(state);
					addContact.setZip(zip);
					addContact.setPhoneNumber(phoneNumber);
					addContact.setEmail(email);
				} catch (DuplicateContactException e) {
					System.out.println(e);
				}	
			}
			
			// Show all contacts that current address book currently having
			addressBooks.get(addressBookName).showContacts();

			// Edit contact from address book contacts according to person details (i.e. first name and last name)
			System.out.println("");
			System.out.println("Enter first name and last name which have to edit contact :");
			// Read person first name and last name
			readPersonDetails();
			// Read contact details with person first name and last name and edit that details in address book
			Person editContact = addressBooks.get(addressBookName).getContact(firstName, lastName);
			if(editContact != null) {
				readContactDetails();
				// edit persons contact details this function can give ContactNotFoundException if persons contact details not found in address book
				editContact.setAddress(address);
				editContact.setCity(city);
				editContact.setState(state);
				editContact.setZip(zip);
				editContact.setPhoneNumber(phoneNumber);
				editContact.setEmail(email);
				System.out.println("Contact details edited");
			} else {
				System.out.println("Contact detail not found");
			}
			
			// Show all contacts that current address book currently having
			addressBooks.get(addressBookName).showContacts();
			
			// Delete contact from address book contacts according to person details (i.e. first name and last name)
			System.out.println("");
			System.out.println("Enter first name and last name which have to delete contact :");
			// Read person first name and last name
			readPersonDetails();
			// Delete that details in address book
			addressBooks.get(addressBookName).deleteContact(firstName, lastName);
			
			// Show all contacts that current address book currently having
			addressBooks.get(addressBookName).showContacts();
			
			// It will not be first address book onwards
			isFirstAddressBook = false;
		}
		
		// Close input object and stop taking inputs from user
		scanner.close();
	}
	
}

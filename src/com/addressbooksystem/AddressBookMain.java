package com.addressbooksystem;

import java.util.*;

public class AddressBookMain {
	// For taking inputs from console
	Scanner scanner = new Scanner(System.in);
	
	// For more than one address book according to address book name
	Hashtable<String, AddressBook> addressBooks = new Hashtable<String, AddressBook>();
	
	// Contact details holding variables
	String firstName, lastName, address, city, state, zip, phoneNumber, email;
	
	/*
	 * first name, last name
	 */
	public void readPersonDetails() {
		System.out.print("First Name : ");
		firstName = scanner.nextLine();
		System.out.print("Last Name : ");
		lastName = scanner.nextLine();
	}
	
	/*
	 * address, city, state, zip, phone number, email
	 */
	public void readContactDetails() {
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
	
	/*
	 * correctly read one number from user and return it 
	 */
	public int readNumber() {
		int number;
		while(true) {
			try {
				number = scanner.nextInt();
				break;
			}catch(InputMismatchException e) {
				System.out.println("Please enter number : ");
			}finally {
				scanner.nextLine();
			}
		}
		return number;
	}
	
	public String[] getAddressBookNames() {
		String addressBookNames[] = new String[addressBooks.size()];
		Iterator<String> keysIterator = addressBooks.keys().asIterator();
		int i = 0;
		while (keysIterator.hasNext()) {
			addressBookNames[i] = (String) keysIterator.next();
			i++;
		}
		return addressBookNames;
	}
	
	/**
	 * add one or more than one addresses to address book which address book name is given as parameter
	 * @param addressBookName
	 */
	public void addAddressesToAddressBook(String addressBookName) {
		// Add contacts to address book 
		System.out.println("");
		System.out.println("Add Contact to "+addressBookName+" address book : ");
		System.out.print("How many persons details you have to add : ");
		int numberOfPerson = readNumber();
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
				setContactDetails(addContact);
			} catch (DuplicateContactException e) {
				System.out.println(e);
			}	
		}
	}
	
	/**
	 * set contact details of persons contact given
	 * @param contact
	 */
	public void setContactDetails(Person contact) {
		readContactDetails();
		contact.setAddress(address);
		contact.setCity(city);
		contact.setState(state);
		contact.setZip(zip);
		contact.setPhoneNumber(phoneNumber);
		contact.setEmail(email);
	}
	
	/**
	 * edit address of address book which name is given as parameter
	 * @param addressBookName
	 */
	public void editAddressOfAddressBook(String addressBookName) {
		// Edit contact from address book contacts according to person details (i.e. first name and last name)
		System.out.println("");
		System.out.println("Edit Contact of "+addressBookName+" address book : ");
		System.out.println("Enter first name and last name which have to edit contact :");
		// Read person first name and last name
		readPersonDetails();
		// Read contact details with person first name and last name and edit that details in address book
		Person editContact = addressBooks.get(addressBookName).getContact(firstName, lastName);
		if(editContact != null) {
			// edit persons contact details this function can give ContactNotFoundException if persons contact details not found in address book
			setContactDetails(editContact);
			System.out.println("Contact details edited");
		} else {
			System.out.println("Contact detail not found");
		}
	}
	
	/**
	 * delete address of address book which name is given as parameter
	 * @param addressBookName
	 */
	public void deleteAddressFromAddressBook(String addressBookName) {
		// Delete contact from address book contacts according to person details (i.e. first name and last name)
		System.out.println("");
		System.out.println("Delete Contact from "+addressBookName+" address book : ");
		System.out.println("Enter first name and last name which have to delete contact :");
		// Read person first name and last name
		readPersonDetails();
		// Delete that details in address book
		addressBooks.get(addressBookName).deleteContact(firstName, lastName);
	}
	
	/**
	 * show all contacts of address book which name is given as parameter
	 * @param addressBookName
	 */
	public void showAddressOfAddressBook(String addressBookName) {
		// Show all contacts that current address book currently having
		System.out.println("Addresess from "+addressBookName+" address book : ");
		addressBooks.get(addressBookName).showContacts();
	}
	
	/**
	 * show options to user and ask which address book have to select 
	 * @return address book name which is selected
	 */
	public String selectAddressBook() {
		String addressBookNames[] = getAddressBookNames();
		System.out.println("Select address book by entering option number : ");
		for (int i = 0; i < addressBookNames.length; i++) {
			System.out.println("\t "+(i + 1)+") "+addressBookNames[i]);
		}
		System.out.print("Enter your option here : ");
		int option = readNumber();
		while(option <= 0 || addressBookNames.length < option) {
			option = readNumber();
		}
		return addressBookNames[option-1];
	}
	
	/*
	 * add address book to dictionary
	 */
	public void addAddressBook() {
		// Makes address book according to name
		System.out.print("Enter name for address book : ");
		String addressBookName = scanner.nextLine();
		addressBooks.put(addressBookName, new AddressBook());
//		System.out.println("MY CHECK : "+addressBookName);
		addAddressesToAddressBook(addressBookName);
	}
	
	public void chooseOperation() {
		while(true) {
			System.out.println("Choose operation : ");
			System.out.println("\t 1) Add address book");
			System.out.println("\t 2) Add contacts to any address book");
			System.out.println("\t 3) Edit contacts of any address book");
			System.out.println("\t 4) Delete contacts from any address book");
			System.out.println("\t 5) Show all contacts");
			System.out.println("\t 6) Show contacts of any address book");
			System.out.println("\t 7) Stop");
			switch(readNumber()) {
			case 1:
				addAddressBook();
				break;
			case 2:
				addAddressesToAddressBook(selectAddressBook());
				break;
			case 3:
				editAddressOfAddressBook(selectAddressBook());
				break;
			case 4:
				deleteAddressFromAddressBook(selectAddressBook());
				break;
			case 5:
				String addressBookNames[] = getAddressBookNames();
				for (String addressBookName : addressBookNames) {
					showAddressOfAddressBook(addressBookName);
				}
				break;
			case 6:
				showAddressOfAddressBook(selectAddressBook());
				break;
			case 7:
				return;
			default:
				System.out.println("Incorrect option entered : ");
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		
		AddressBookMain addressBookMain = new AddressBookMain();
		addressBookMain.addAddressBook();
		addressBookMain.chooseOperation();
	}	
}

package com.addressbooksystem;

import java.util.Scanner;

public class AddressBookMain {
	static Scanner scanner = new Scanner(System.in);
	static String firstName, lastName, address, city, state, zip, phoneNumber, eamil;
	
	public static void readPersonDetails() {
		System.out.print("First Name : ");
		firstName = scanner.nextLine();
		System.out.print("Last Name : ");
		lastName = scanner.nextLine();
	}
	
	public static void readContactDetails() {
		readPersonDetails();
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
		eamil = scanner.nextLine();
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		
		AddressBook addressBook = new AddressBook();
		System.out.println("Enter 4 person details: ");
		for (int number = 1; number <= 2; number++) {
			System.out.println("");
			System.out.println("Person "+number+" : ");
			readContactDetails();
			addressBook.addContact(firstName, lastName, address, city, state, zip, phoneNumber, eamil);	
		}
		addressBook.showContacts();
		System.out.println("");
		System.out.println("Enter first name and last name which have to edit contact :");
		readPersonDetails();
		int indexInContact = addressBook.indexOfContact(firstName, lastName);
		if (indexInContact != -1) {
			System.out.println("Contact exists you can edit");
			readContactDetails();
			addressBook.editContact(indexInContact, firstName, lastName, address, city, state, zip, phoneNumber, address);
		} else {
			System.out.println("Contact not exists you can not edit");
		}
		addressBook.showContacts();
		scanner.close();
	}
	
}

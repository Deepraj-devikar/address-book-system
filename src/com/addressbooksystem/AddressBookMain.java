package com.addressbooksystem;

import java.util.Scanner;

public class AddressBookMain {

	public static void main(String[] args) {
		String firstName, lastName, address, city, state, zip, phoneNumber, eamil;
		System.out.println("Welcome to Address Book Program");
		Scanner scanner = new Scanner(System.in);
		AddressBook addressBook = new AddressBook();
		System.out.println("Enter person details: ");
		System.out.print("First Name : ");
		firstName = scanner.nextLine();
		System.out.print("Last Name : ");
		lastName = scanner.nextLine();
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
		addressBook.addContact(firstName, lastName, address, city, state, zip, phoneNumber, eamil);
		addressBook.showContacts();
		scanner.close();
	}

}

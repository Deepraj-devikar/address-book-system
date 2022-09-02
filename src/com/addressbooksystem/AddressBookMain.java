package com.addressbooksystem;

public class AddressBookMain {

	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");
		AddressBook addressBook = new AddressBook();
		addressBook.addContact("Rajendra", "Ninawe", "Pagalkhana sq.", "Ahamdabad", "Gujrat", "38125", "+91 8759632457", "rajendra@address.com");
		addressBook.showContacts();
	}

}

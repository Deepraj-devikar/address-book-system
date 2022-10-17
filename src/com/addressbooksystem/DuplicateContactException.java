package com.addressbooksystem;

public class DuplicateContactException extends Exception{
	public DuplicateContactException(String firstName, String lastName) {
		super("Alredy found contact with first name :- "+firstName+" and last name :- "+lastName);
	}
}

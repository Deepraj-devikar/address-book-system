package com.addressbooksystem.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.addressbooksystem.Person;

@RunWith(Parameterized.class)
public class PersonTest {
	private String firstName, lastName, address, city, state, zip, phoneNumber, email;
	
	private String editFirstName, editLastName, editAddress, editCity, editState, editZip, editPhoneNumber, editEmail;
	
	private String expectFirstName, expectLastName, expectAddress, expectCity, expectState, expectZip, expectPhoneNumber, expectEmail;
	
	private Person person;
	
	public PersonTest(
			String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email,
			String editFirstName, String editLastName, String editAddress, String editCity, String editState, String editZip, 
			String editPhoneNumber, String editEmail, 
			String expectFirstName, String expectLastName, String expectAddress, String expectCity, String expectState, String expectZip, 
			String expectPhoneNumber, String expectEmail
			) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
		this.email = email;
		
		this.editFirstName = editFirstName;
		this.editLastName = editLastName;
		this.editAddress = editAddress;
		this.editCity = editCity;
		this.editState = editState;
		this.editZip = editZip;
		this.editPhoneNumber = editPhoneNumber;
		this.editEmail = editEmail;
		
		this.expectFirstName = expectFirstName;
		this.expectLastName = expectLastName;
		this.expectAddress = expectAddress;
		this.expectCity = expectCity;
		this.expectState = expectState;
		this.expectZip = expectZip;
		this.expectPhoneNumber = expectPhoneNumber;
		this.expectEmail = expectEmail;
	}
	
	@Parameterized.Parameters
	public static Collection<String[]> testPerson() {
		return Arrays.asList(new String[][] {
			{"abc", "def", "a", "a", "a", "a", "a", "a", "abc", "def", "", "", "b", "b", "b", "b", "abc", "def", "a", "a", "b", "b", "b", "b"}
		});
	}
	
	@Test
	public void test() {
		person = new Person(firstName, lastName);
		person.setAddress(address);
		person.setCity(city);
		person.setState(state);
		person.setZip(zip);
		person.setPhoneNumber(phoneNumber);
		person.setEmail(email);
		
		person.setAddress(editAddress);
		person.setCity(editCity);
		person.setState(editState);
		person.setZip(editZip);
		person.setPhoneNumber(editPhoneNumber);
		person.setEmail(editEmail);
		
		Assert.assertEquals(expectAddress, person.getAddress());
		Assert.assertEquals(expectCity, person.getCity());
		Assert.assertEquals(expectState, person.getState());
		Assert.assertEquals(expectZip, person.getZip());
		Assert.assertEquals(expectPhoneNumber, person.getPhoneNumber());
		Assert.assertEquals(expectEmail, person.getEmail());
	}

}

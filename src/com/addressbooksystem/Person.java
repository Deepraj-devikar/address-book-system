package com.addressbooksystem;

public class Person {
	private String firstName, lastName, address, city, state, zip, phoneNumber, email;
	
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = "";
		this.city = "";
		this.state = "";
		this.zip = "";
		this.phoneNumber = "";
		this.email = "";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName != "") this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName != "") this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (address != "") this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		if (city != "") this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		if (state != "") this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		if (zip != "") this.zip = zip;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		if (phoneNumber != "") this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email != "") this.email = email;
	}
	
	@Override
	public boolean equals(Object person) {
		if(person instanceof Person) {
			Person newPerson = (Person) person;
			return this.firstName.equals(newPerson.firstName) && this.lastName.equals(newPerson.lastName);
		} else {
			return false;
		}
	}
	
	public int compareToByName(Person anotherPerson) {
		return (firstName+" "+lastName).compareToIgnoreCase(anotherPerson.firstName+" "+anotherPerson.lastName);
	}
	
	public int compareToByCity(Person anotherPerson) {
		return city.compareToIgnoreCase(anotherPerson.city);
	}
	
	public int compareToByState(Person anotherPerson) {
		return state.compareToIgnoreCase(anotherPerson.state);
	}
	
	public int compareToByZip(Person anotherPerson) {
		return zip.compareToIgnoreCase(anotherPerson.zip);
	}
	
	@Override
	public String toString() {
		return "\n"+getFirstName()+" "+getLastName()+" : \n"
				+getAddress()+", \n"
				+getCity()+", "+getState()+" - "+getZip()+"\n"
				+getPhoneNumber()+"\n"
				+getEmail()+"\n";
	}
}

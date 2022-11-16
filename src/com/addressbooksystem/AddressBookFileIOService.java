package com.addressbooksystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class AddressBookFileIOService {
	private final String HOME_DIRECTORY_PATH = "E:\\bridgelabz\\data"; 
	private String addressBookName;
	
	public AddressBookFileIOService(String addressBookName) {
		this.addressBookName = addressBookName;
	}
	
	/**
	 * check is valid file path 
	 * if file path is not valid then create empty file and say valid file path
	 * if unable to create empty file due to IOException then say not valid file path
	 * 
	 * @param filePath
	 * @return valid file path then true otherwise false
	 */
	public boolean isValidFilePath(Path filePath) {
		if(!Files.exists(filePath)) {
			try {
				Files.createFile(filePath);
				return true;
			} catch (IOException e) {
				System.out.println("Unable to create file exception is : "+e);
				return false;
			}
		} else {
			return true;
		}
	}
	
	/**
	 * check is valid directory
	 * if directory is not valid then create empty directory and say valid directory
	 * if unable to create directory due to IOException then say not valid directory
	 *  
	 * @param filePath
	 * @return valid directory path then true otherwise false
	 */
	public boolean isValidDirectory(Path directoryPath) {
		if(!Files.exists(directoryPath)) {
			try {
				Files.createDirectories(directoryPath);
				return true;
			} catch (IOException e) {
				System.out.println("Unable to create directory exception is : "+e);
				return false;
			}
		} else {
			return true;
		}
	}
	
	/**
	 * write persons data to text file
	 * 
	 * @param personData
	 */
	public void writeData(ArrayList<Person> personData) {
		if(isValidDirectory(Paths.get(HOME_DIRECTORY_PATH + "\\" + addressBookName))) {
			Path filePath = Paths.get(HOME_DIRECTORY_PATH + "\\" + addressBookName + "\\data.txt");
			if(isValidFilePath(filePath)) {
				StringBuffer personInformation = new StringBuffer();
				personData.forEach(currentPersonInformation -> {
					personInformation.append("Information of "+currentPersonInformation.toString()+"\n");
				});
				try {
					Files.write(filePath, personInformation.toString().getBytes());
					System.out.println(personData.size()+" contact persons information written to "+filePath.toString()+" file");
				} catch (IOException e) {
					System.out.println("Unable to write contact persons information to "+filePath.toString()+" file");
					System.out.println("Exception is : "+e);
				}	
			}
		}
	}
	
	/**
	 * read persons data from text file
	 * 
	 * @return persons data
	 */
	public ArrayList<Person> readData() {
		Path filePath = Paths.get(HOME_DIRECTORY_PATH + "\\" + addressBookName + "\\data.txt");
		ArrayList<String> firstNames = new ArrayList<String>();
		ArrayList<String> lastNames = new ArrayList<String>();
		ArrayList<String> addresses = new ArrayList<String>();
		ArrayList<String> cities = new ArrayList<String>();
		ArrayList<String> states = new ArrayList<String>();
		ArrayList<String> zips = new ArrayList<String>();
		ArrayList<String> phoneNumbers = new ArrayList<String>();
		ArrayList<String> emails = new ArrayList<String>();
		String informationIdentifier = "information of";
		if(Files.exists(filePath)) {
			try {
				Iterator<String> data = Files.readAllLines(filePath).listIterator();
				int lineCountFromStart = -1;
				while(data.hasNext()) {
					String line = data.next();
					if(line.trim().toLowerCase().equals(informationIdentifier)){
						lineCountFromStart = 0;
					} else if(lineCountFromStart == 0) {
						String[] nameArray = line.trim().split(" ");
						firstNames.add(nameArray[0]);
						lastNames.add(nameArray[1].substring(0, nameArray[1].length()));
						lineCountFromStart++;
					} else if(lineCountFromStart == 1) {
						String trimedLine = line.trim();
						addresses.add(trimedLine.substring(0, trimedLine.length() - 1));
						lineCountFromStart++;
					} else if(lineCountFromStart == 2) {
						String[] cityStateZip = line.trim().split(", ");
						cities.add(cityStateZip[0].trim());
						String[] stateZip = cityStateZip[1].trim().split(" - ");
						states.add(stateZip[0].trim());
						zips.add(stateZip[1].trim());
						lineCountFromStart++;
					} else if(lineCountFromStart == 3) {
						phoneNumbers.add(line.trim());
						lineCountFromStart++;
					} else if(lineCountFromStart == 4) {
						emails.add(line.trim());
						lineCountFromStart = -1;
					}
				}
			} catch (IOException e) {
				System.out.println("Unable to read contacts from file "+filePath);
				System.out.println("Exception is "+e);
			}	
		} else {
			System.out.println(filePath+" not exists");
		}
		ArrayList<Person> contacts = new ArrayList<Person>();
		long minimumData = firstNames.size();
		if(minimumData > lastNames.size()) {
			minimumData = lastNames.size();
		}
		if(minimumData > addresses.size()) {
			minimumData = addresses.size();
		}
		if(minimumData > cities.size()) {
			minimumData = cities.size();
		}
		if(minimumData > states.size()) {
			minimumData = states.size();
		}
		if(minimumData > zips.size()) {
			minimumData = zips.size();
		}
		if(minimumData > phoneNumbers.size()) {
			minimumData = phoneNumbers.size();
		}
		if(minimumData > emails.size()) {
			minimumData = emails.size();
		}
		for(int i = 0; i < minimumData; i++) {
			Person tempPersonData = new Person(firstNames.get(i), lastNames.get(i));
			tempPersonData.setAddress(addresses.get(i));
			tempPersonData.setCity(cities.get(i));
			tempPersonData.setState(states.get(i));
			tempPersonData.setZip(zips.get(i));
			tempPersonData.setPhoneNumber(phoneNumbers.get(i));
			tempPersonData.setEmail(emails.get(i));
			contacts.add(tempPersonData);
		}
		
		System.out.println("data found in "+filePath.toString()+" file : ");
		System.out.println(contacts);
		return contacts;
	}
}

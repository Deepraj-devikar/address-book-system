package com.addressbooksystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

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
	
	public void writeData(ArrayList<Person> contacts) {
		writeDataToTextFile(contacts);
		writeDataToCSVFile(contacts);
		writeDataToJSONFile(contacts);
	}
	
	/**
	 * write persons data to text file
	 * 
	 * @param personData
	 */
	public void writeDataToTextFile(ArrayList<Person> contacts) {
		if(isValidDirectory(Paths.get(HOME_DIRECTORY_PATH + "\\" + addressBookName))) {
			Path filePath = Paths.get(HOME_DIRECTORY_PATH + "\\" + addressBookName + "\\data.txt");
			if(isValidFilePath(filePath)) {
				StringBuffer personInformation = new StringBuffer();
				contacts.forEach(currentPersonInformation -> {
					personInformation.append("Information of "+currentPersonInformation.toString()+"\n");
				});
				try {
					Files.write(filePath, personInformation.toString().getBytes());
					System.out.println(contacts.size()+" contact persons information written to text file "+filePath.toString()+" file.");
				} catch (Exception exception) {
					System.out.println("Unable to write contact persons information to "+filePath.toString()+" file.");
					System.out.println("Exception is : "+exception);
				}	
			}
		}
	}
	
	/**
	 * write persons data to CSV file
	 * 
	 * @param personData
	 */
	public void writeDataToCSVFile(ArrayList<Person> contacts) 
			 {
		if(isValidDirectory(Paths.get(HOME_DIRECTORY_PATH + "\\" + addressBookName))) {
			Path filePath = Paths.get(HOME_DIRECTORY_PATH + "\\" + addressBookName + "\\data.csv");
			try(
					Writer writer = Files.newBufferedWriter(filePath)
					){
				
				StatefulBeanToCsv<CSVPerson> beanToCSV = new StatefulBeanToCsvBuilder<CSVPerson>(writer)
						.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
						.build();
				
				ArrayList<CSVPerson> persons = new ArrayList<CSVPerson>(contacts
						.stream()
						.map(tempPerson -> tempPerson.getCSVPerson())
						.collect(Collectors.toList()));
				beanToCSV.write(persons);
				writer.close();
				System.out.println(contacts.size()+" contact persons information written to CSV file "+filePath.toString()+" file");
			} catch (Exception exception) {
				System.out.println("Unable to write contact persons information to "+filePath.toString()+" file");
				System.out.println("Exception is : "+exception);
			}
		}
	}
	
	/**
	 * write persons data to JSON file
	 * 
	 * @param personData
	 */
	public void writeDataToJSONFile(ArrayList<Person> contacts) {
		if(isValidDirectory(Paths.get(HOME_DIRECTORY_PATH + "\\" + addressBookName))) {
			String filePathString = HOME_DIRECTORY_PATH + "\\" + addressBookName + "\\data.json";
			Path filePath = Paths.get(filePathString);
			if(isValidFilePath(filePath)) {
				try {
					Gson gson = new Gson();
					ArrayList<JSONPerson> persons = new ArrayList<JSONPerson>(contacts
							.stream()
							.map(tempPerson -> tempPerson.getJSONPerson())
							.collect(Collectors.toList()));
					String json = gson.toJson(persons);
					
					FileWriter writer = new FileWriter(filePathString);
					writer.write(json);
					writer.close();
					System.out.println(contacts.size()+" contact persons information written to JSON file "+filePath.toString()+" file");
				} catch (Exception exception) {
					System.out.println("Unable to write contact persons information to "+filePath.toString()+" file.");
					System.out.println("Exception is : "+exception);
				}
			}
		}
	}
	
	public ArrayList<Person> readData(){
		ArrayList<Person> contacts = new ArrayList<Person>();
		switch((int) Math.floor(Math.random() * 10) % 3) {
		case 0:
			contacts = readDataFromTextFile();
			break;
		case 1:
			contacts = readDataFromCSVFile();
			break;
		case 2:
			contacts = readDataFromJSONFile();	
			break;
		}
		return contacts;
	}
	
	/**
	 * read persons data from text file
	 * 
	 * @return persons data
	 */
	public ArrayList<Person> readDataFromTextFile() {
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
			} catch (Exception exception) {
				System.out.println("Unable to read contacts from file "+filePath);
				System.out.println("Exception is "+exception);
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
	
	public ArrayList<Person> readDataFromCSVFile(){
		ArrayList<Person> contacts = new ArrayList<Person>();
		if(isValidDirectory(Paths.get(HOME_DIRECTORY_PATH + "\\" + addressBookName))) {
			Path filePath = Paths.get(HOME_DIRECTORY_PATH + "\\" + addressBookName + "\\data.csv");
			if(Files.exists(filePath)) {
				try (
						Reader reader = Files.newBufferedReader(filePath);
						) {

					CsvToBean<CSVPerson> csvToBean = new CsvToBeanBuilder<CSVPerson>(reader)
		                    .withType(CSVPerson.class)
		                    .withIgnoreLeadingWhiteSpace(true)
		                    .build();
					reader.close();
		            List<CSVPerson> persons = csvToBean.parse();
		            persons.forEach(person -> contacts.add(new Person(person)));
		            System.out.println("data found in "+filePath.toString()+" file : ");
		    		System.out.println(contacts);
				} catch (IOException exception) {
					System.out.println("Unable to read contacts from file "+filePath);
					System.out.println("Exception is "+exception);
				}
			} else {
				System.out.println(filePath+" not exists");
			}
		} else {
			System.out.println("data not exists.");
		}
		return contacts;
	}
	
	public ArrayList<Person> readDataFromJSONFile(){
		ArrayList<Person> contacts = new ArrayList<Person>();
		if(isValidDirectory(Paths.get(HOME_DIRECTORY_PATH + "\\" + addressBookName))) {
			String filePathString = HOME_DIRECTORY_PATH + "\\" + addressBookName + "\\data.json";
			Path filePath = Paths.get(filePathString);
			if(Files.exists(filePath)) {
				try(
						BufferedReader bufferedReader = new BufferedReader(new FileReader(filePathString));
						){
					Gson gson = new Gson();
					JSONPerson[] personObjects = gson.fromJson(bufferedReader, JSONPerson[].class);
					bufferedReader.close();
					for(int personIndex = 0; personIndex < personObjects.length; personIndex++) {
						contacts.add(new Person(personObjects[personIndex]));
					}
					System.out.println("data found in "+filePath.toString()+" file : ");
		    		System.out.println(contacts);
				} catch(Exception exception) {
					System.out.println("Unable to read contacts from file "+filePath);
					System.out.println("Exception is "+exception);
				}
			} else {
				System.out.println(filePath+" not exists");
			}
		} else {
			System.out.println("data not exists.");
		}
		return contacts;
	}
}

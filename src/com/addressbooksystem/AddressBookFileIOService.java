package com.addressbooksystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AddressBookFileIOService {
	private final String HOME_DIRECTORY_PATH = "E:\\bridgelabz\\data"; 
	private String addressBookName;
	
	public AddressBookFileIOService(String addressBookName) {
		this.addressBookName = addressBookName;
	}
	
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
	
	public boolean isValidDirectory(Path filePath) {
		if(!Files.exists(filePath)) {
			try {
				Files.createDirectories(filePath);
				return true;
			} catch (IOException e) {
				System.out.println("Unable to create directory exception is : "+e);
				return false;
			}
		} else {
			return true;
		}
	}
	
	public void writeData(ArrayList<Person> personData) {
		if(isValidDirectory(Paths.get(HOME_DIRECTORY_PATH + "\\" + addressBookName))) {
			Path filePath = Paths.get(HOME_DIRECTORY_PATH + "\\" + addressBookName + "\\data.txt");
			if(isValidFilePath(filePath)) {
				StringBuffer personInformation = new StringBuffer();
				personData.forEach(currentPersonInformation -> {
					personInformation.append("Information of "+currentPersonInformation.toString());
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
}

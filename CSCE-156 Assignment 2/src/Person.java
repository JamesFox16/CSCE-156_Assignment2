import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*import com.google.gson.Gson;
import com.google.gson.GsonBuilder;*/

public class Person {
	
	private String personCode;
	private String name;
	private Address address;
	private ArrayList<String> emails;
	
	//Constructor
	public Person(String personCode, String name, Address address) {
		this.personCode = personCode;
		this.name = name;
		this.address = address;
	}
	
	//Getter/Setter for personCode
	public String getPersonCode() {
		return this.personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	
	//Getter/Setter for firstName
	public String getFirstName() {
		return this.name;
	}
	public void setFirstName(String name) {
		this.name = name;
	}
	
	//Getter/Setter for address
	public Address getAddress() {
		return this.address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public ArrayList<String> getEmailAddress() {
		return emails;
	}
	
	public void setEmailAddress(ArrayList<String> emails) {
		this.emails = emails;
	}
	
	/* Setup to make XML/Json writers interfaces
	public void xmlConverterPerson(List<Person> persons) {
        File xmlOutput = new File("data/Persons.xml");// The location of the data file.
		
		PrintWriter xmlPrintWriter = null;
		try {
			xmlPrintWriter = new PrintWriter(xmlOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		//xstream.alias("person", Person.class); 
		for(Person aPerson : persons) {
			// Use toXML method to convert Person object into a String
			String personOutput = xstream.toXML(aPerson);
			xmlPrintWriter.write(personOutput+"\n");// new line for better formatting
		}
		xmlPrintWriter.close();	
	}
	public void jsonConverterPerson(List<Person> persons) {
		
		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		File jsonOutput = new File("data/Persons.json");
		
		PrintWriter jsonPrintWriter = null;
		
		try {
			jsonPrintWriter = new PrintWriter(jsonOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		// Loop to go through all persons in the list.
		for(Person aPerson : persons) {
			// Use toJson method to convert Person object into a String
			
			String personOutput = gson.toJson(aPerson); 
			jsonPrintWriter.write(personOutput + "\n");
		}
		
		jsonPrintWriter.close();
	}
	*/

}

import java.util.ArrayList;

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

}

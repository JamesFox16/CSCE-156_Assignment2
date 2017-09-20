
public class Person {
	
	private String personCode;
	private String firstName;
	private String lastName;
	private Address address;
	
	//Constructor
	public Person(String personCode, String firstName, String lastName, Address address) {
		super();
		this.personCode = personCode;
		this.firstName = firstName;
		this.lastName = lastName;
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
		return this.firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	//Getter/Setter for lastName
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	//Getter/Setter for address
	public Address getAddress() {
		return this.address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

}

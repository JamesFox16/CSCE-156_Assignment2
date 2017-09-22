
public class Customer {
	
	private String customerCode;
	private String type;
	private Person person;
	private String name;
	private Address address;
	//private String primaryContact;
	
	//Constructor
	public Customer(String customerCode, String type, Person person, String name, Address address) {
		//super(customerCode, name, address);
		this.customerCode = customerCode;
		this.type = type;
		this.person = person;
		this.name = name;
		this.address = address;
		//this.primaryContact = primaryContact;
	}
	
	//Getter/Setter for customerCode
	public String getCustomerCode() {
		return this.customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
	//Getter/Setter for type
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	//Getter/Setter for person
	public Person getPerson() {
		return this.person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
	//Getter/Setter for name
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//Getter/Setter for address
	public Address getAddress() {
		return this.address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}

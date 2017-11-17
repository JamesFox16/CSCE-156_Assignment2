package cinac;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/*import com.google.gson.Gson;
import com.google.gson.GsonBuilder;*/

public class Customer{
	
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
	
	//At invoice report, multiply subtotal by this method then add together
	public double computeTax() {
		return 0;
	}
	
	//At invoice report, multiply subtotal by this method then add together
	public double computeDiscount() {
		if(this.getType().equals("S")) {
			return 6.75;
		}else {
			return 1;
		}
	}
	
	//At invoice report, add this function to total
	public double addFees() {
		if(this.getType().equals("S")) {
			return 6.75;
		}else {
			return 0;
		}
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

import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomerCreator {
	
	private List<Person> personList = new ArrayList<Person>();
	private List<Customer> customerList = new ArrayList<Customer>();
	private List<Product> productList = new ArrayList<Product>();
	XMLWriter xmlw = new XMLWriter();
	JsonWriter jsonw = new JsonWriter();
	
	public CustomerCreator() {
		
	}
	
	//Create a list of Person objects
	public List<Person> createPersonList() throws IOException{
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/Persons.dat"));
			br.readLine();
			
			while(br.ready()) {
				String line = br.readLine();
				String info[] = line.split(";");
				
				if(info.length == 4) { //Email not yet included in Person class
					String personCode = info[0];
					String name[] = info[1].split(",");
					String first = name[0];
					String last = name[1];
					String addr[] = info[2].split(",");
					String nameFormat = last+", "+first;
					Address address = new Address(addr[0],addr[1],addr[2],addr[3],addr[4]);
					String email[] = info[3].split(",");
					ArrayList<String> emails = new ArrayList<String>();
					for(int i=0; i<email.length; i++) {
						emails.add(email[i]);
					}
					Person p = new Person(personCode, nameFormat, address);
					p.setEmailAddress(emails);
					personList.add(p);
				}
				else {
					String personCode = info[0];
					String name[] = info[1].split(",");
					String first = name[0];
					String last = name[1];
					String addr[] = info[2].split(",");
					String nameFormat = last+", "+first;
					Address address = new Address(addr[0],addr[1],addr[2],addr[3],addr[4]);

					Person p = new Person(personCode, nameFormat, address);
					personList.add(p);
				}
			}
			
			br.close();
			xmlw.xmlConverterPerson(personList);
			jsonw.jsonConverterPerson(personList);
			return personList;
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Create a list of Customer objects
	public List<Customer> createCustomerList() throws IOException{
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/Customers.dat"));
			br.readLine();
			
			while(br.ready()) {
				String line = br.readLine();
				String info[] = line.split(";");
				
				String customerCode = info[0];
				String type = info[1];
				String primaryContact = info[2];
				String name = info[3];
				String addr[] = info[4].split(",");
					Address address = new Address(addr[0],addr[1],addr[2],addr[3],addr[4]);
				
				Person p = findPerson(primaryContact);
				
				Customer c = new Customer(customerCode, type, p, name, address);
				
				customerList.add(c);
			}
			
			br.close();
			xmlw.xmlConverterCustomer(customerList);
			jsonw.jsonConverterCustomer(customerList); //primaryContact to Person link not working?
			return customerList;
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Create a list of Product objects
	public List<Product> createProductList() throws IOException{
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/Products.dat"));
			br.readLine();
			
			while(br.ready()) {
				String line = br.readLine();
				String info[] = line.split(";"); //No Product constructors have been written yet
				
				if(info.length == 7) { //Movie Ticket
					String code = info[0];
					String type = info[1];
					String dateTime = info[2];
					String movieName = info[3];
					String addr[] = info[4].split(",");
						Address address = new Address(addr[0],addr[1],addr[2],addr[3],addr[4]);
					String screenNumber = info[5];
					String pricePerUnit = info[6];
					
					Product p = new Product(code,type,dateTime,movieName,address,screenNumber,pricePerUnit);
					productList.add(p);
					
				}else if(info.length == 6) { //Season Pass
					String code = info[0];
					String type = info[1];
					String name = info[2];
					String startDate = info[3];
					String endDate = info[4];
					String cost = info[5];
					
					Product p = new Product(code,type,name,startDate,endDate,cost);
					productList.add(p);
					
				}else if(info.length == 3) { //Parking Pass
					String code = info[0];
					String type = info[1];
					String parkingFee = info[2];
					
					Product p = new Product(code,type,parkingFee);
					productList.add(p);
					
				}else if(info.length == 4) { //Refreshments
					String code = info[0];
					String type = info[1];
					String name = info[2];
					String cost = info[3];
					
					Product p = new Product(code,type,name,cost);
					productList.add(p);
					
				}
			}
			//XML/JSON writers for Products go here
			br.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Finds a Person from the personList based on the personCode
	public Person findPerson(String personCode) {
		for(Person p : personList) {
			if(personCode.equals(p.getPersonCode())) {
				return p;
			}
		}
		return null;
	}

}

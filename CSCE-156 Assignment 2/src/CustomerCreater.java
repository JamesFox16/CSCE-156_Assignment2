import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomerCreater {
	
	private List<Person> personList = new ArrayList<Person>();
	private List<Customer> customerList = new ArrayList<Customer>();
	
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
						Address address = new Address(addr[0],addr[1],addr[2],addr[3],addr[4]);
					String email[] = info[3].split(",");
					
					Person p = new Person(personCode,first,last,address);
					personList.add(p);
				}
				else {
					String personCode = info[0];
					String name[] = info[1].split(",");
						String first = name[0];
						String last = name[1];
					String addr[] = info[2].split(",");
						Address address = new Address(addr[0],addr[1],addr[2],addr[3],addr[4]);

					Person p = new Person(personCode,first,last,address);
					personList.add(p);
				}
			}
			
			br.close();
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
			return customerList;
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

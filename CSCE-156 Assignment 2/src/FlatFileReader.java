import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FlatFileReader {
	
	// Creating the lists that will be used and returned.
	private List<Person> personList = new ArrayList<Person>();
	private List<Customer> customerList = new ArrayList<Customer>();
	private List<Product> productList = new ArrayList<Product>();
	private List<Invoice> invoiceList = new ArrayList<Invoice>();
	
	// Default constructor 
	public FlatFileReader() {
		
	}
	
	
	//Create a list of Person objects
	public List<Person> createPersonList() throws IOException{
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/Persons.dat"));
			br.readLine();
			
			while(br.ready()) {
				String line = br.readLine();
				String info[] = line.split(";");
				// If else statements determining format that will be used 
				// when creating the objects.
				if(info.length == 4) {
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
				else if (info.length==3){
					String personCode = info[0];
					String name[] = info[1].split(",");
					String first = name[0];
					String last = name[1];
					String addr[] = info[2].split(",");
					String nameFormat = last+", "+first;
					Address address = new Address(addr[0],addr[1],addr[2],addr[3],addr[4]);

					Person p = new Person(personCode, nameFormat, address);
					personList.add(p);
				}else {
					//Bad formating
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
	
	//Create a list of Product objects
	public List<Product> createProductList() throws IOException{
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/Products.dat"));
			br.readLine();
		
			while(br.ready()) {
				String line = br.readLine();
				String info[] = line.split(";");
			
				// If else statements determining format that will be used 
				// when creating the objects.
				if(info.length == 7) { //Movie Ticket
					String productCode = info[0];
					String productType = info[1];
					String dateTime = info[2];
					String name = info[3];
					String addr[] = info[4].split(",");
					Address address = new Address(addr[0],addr[1],addr[2],addr[3],addr[4]);
					String screenNumber = info[5];
					double unitPrice = Double.parseDouble(info[6]);
				
					MovieTicket m = new MovieTicket(productCode, productType, dateTime, name, address, screenNumber, unitPrice);
					productList.add(m);
				
				}else if(info.length == 6) { //Season Pass
					String productCode = info[0];
					String productType = info[1];
					String name = info[2];
					String dateTime = info[3];
					String endDate = info[4];
					double unitPrice = Double.parseDouble(info[5]);
				
					SeasonPass sp = new SeasonPass(productCode, productType, name, dateTime, endDate, unitPrice);
					productList.add(sp);
				
				}else if(info.length == 3) { //Parking Pass
					String productCode = info[0];
					String productType = info[1];
					double unitPrice = Double.parseDouble(info[2]);
				
					ParkingPass pp = new ParkingPass(productCode, productType, unitPrice);
					productList.add(pp);
				
				}else if(info.length == 4) { //Refreshments
					String productCode = info[0];
					String productType = info[1];
					String name = info[2];
					double unitPrice = Double.parseDouble(info[3]);
				
					Refreshments r = new Refreshments(productCode, productType, name, unitPrice);
					productList.add(r);
				
				}
			}
			br.close();
			return productList;
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			return null;
	}
	
	//Creates a list of invoices each as objects.
	public List<Invoice> createInvoiceList() throws IOException {
		//TODO: fill in code here.
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("data/Invoices.dat"));
			br.readLine();
			
			while (br.ready()) {
				String line = br.readLine();
				String info[] = line.split(";");
				String invoiceCode = info[0];
				String customerCode = info[1];
				String salespersonCode = info[2];
				String date = info[3];
				
				List<Product> productsForInvoice = new ArrayList<Product>();
				List<String> quantityOfPurchases = new ArrayList<String>();
				String[] purchases = info[4].split(",");
				for(int i=0; i<purchases.length; i++) {
					
					String[] hold = purchases[i].split(":");
					String productCode = hold[0];
					productsForInvoice.add(findProduct(productCode));
					String quantity = hold[1];
					quantityOfPurchases.add(quantity);
					
					if(hold.length == 3) {
						String ticketCode = hold[2];
					}
				}
				Person p = findPerson(salespersonCode);
				
				//Implement quantity of products purchased somehow...
				Invoice j = new Invoice(invoiceCode, findCustomer(customerCode), p, date, productsForInvoice, quantityOfPurchases);
				invoiceList.add(j);
				
			}
			
			br.close();
			return invoiceList;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	
	//Finds a Customer from the customerList based on the customerCode
		public Customer findCustomer(String customerCode) {
			for(Customer c : customerList) {
				if(customerCode.equals(c.getCustomerCode())) {
					return c;
				}
			}
			return null;
		}
	
	//Finds a product from the personList based on the personCode
	public Product findProduct(String productCode) {
		for(Product p : productList) {
			if(productCode.equals(p.getProductCode())) {
				return p;
			}
		}
		return null;
	}

}

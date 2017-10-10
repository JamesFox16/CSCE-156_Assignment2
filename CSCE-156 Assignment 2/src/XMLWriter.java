
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class XMLWriter{
	
	private XStream xstream = null;
	
	
	public XMLWriter() {
		this.xstream = new XStream();
		
		//Aliases for xstream
		xstream.alias("person", Person.class);
		xstream.alias("email", String.class);
		xstream.alias("unitPrice", Product.class);
	
	}
	
	// Method to convert the list of Person objects to XML.
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
	
	// Method to convert the list of Person objects to XML.
	public void xmlConverterCustomer(List<Customer> customers) {
        File xmlOutput = new File("data/Customers.xml");// The location of the data file.
		
		PrintWriter xmlPrintWriter = null;
		try {
			xmlPrintWriter = new PrintWriter(xmlOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		//xstream.alias("customer", Customer.class); 
		for(Customer aCustomer : customers) {
			// Use toXML method to convert Customer object into a String
			String personOutput = xstream.toXML(aCustomer);
			xmlPrintWriter.write(personOutput+"\n");// new line for better formatting
		}
		xmlPrintWriter.close();	
	}
	
	// Method to convert the list of Person objects to XML.
	public void xmlConvertProducts(List<Product> products) {
		File xmlOutput = new File("data/Products.xml");// The location of the data file.
		PrintWriter xmlPrintWriter = null;
		
		try {
			xmlPrintWriter = new PrintWriter(xmlOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Sets up the header for the xml file
		xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		// Loop to go throught the list of products
		for(Product aProduct : products) {
			String productOut = xstream.toXML(aProduct);
			xmlPrintWriter.write(productOut+"\n");// new line for better formatting
		}
		xmlPrintWriter.close();
	}
	
}

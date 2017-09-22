
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonWriter {
	
	// Method to convert Person list into json file
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
	
	// Method to convert Customer list into json file
	public void jsonConverterCustomer(List<Customer> customers) {
		
		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		File jsonOutput = new File("data/Customers.json");
		
		PrintWriter jsonPrintWriter = null;
		
		try {
			jsonPrintWriter = new PrintWriter(jsonOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		// Loop to go through all customers in the list.
		for(Customer aCustomer : customers) {
			// Use toJson method to convert Customer object into a String
			
			String customerOutput = gson.toJson(aCustomer); 
			jsonPrintWriter.write(customerOutput + "\n");
		}
		
		jsonPrintWriter.close();
	}
	
	// Method to convert Product list into json file
	public void jsonConverterProduct(List<Product> products) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		File jsonOutput = new File("data/Products.json");
		
		PrintWriter jsonPrintWriter = null;
		
		try {
			jsonPrintWriter = new PrintWriter(jsonOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Loop to go through all products in the list.
		for(Product aProduct : products) {
			String productOutput = gson.toJson(aProduct);
			jsonPrintWriter.write(productOutput+"\n");
			
		}
		jsonPrintWriter.close();
		
	}
}

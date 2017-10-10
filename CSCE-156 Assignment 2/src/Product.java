import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Product {

	private String productCode;
	private String productType;
	private double price;
	//private int quantity;
	
	public Product(String productCode, String productType, double unitPrice) {
		this.productCode = productCode;
		this.productType = productType;
		this.price = unitPrice;
	}
	
	public double computeTax() {
		return 0;
	}
	
	public double getProductPrice() {
		return this.price;
	}
	
	//Getter/Setter for productCode
	public String getProductCode() {
		return this.productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	//Getter/Setter for productType
	public String getProductType() {
		return this.productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	/* Setup to make XML/Json writers interfaces
	public void xmlConverter(List<Product> products) {
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
		for(Object aProduct : products) {
			String productOut = xstream.toXML(aProduct);
			xmlPrintWriter.write(productOut+"\n");// new line for better formatting
		}
		xmlPrintWriter.close();
	}
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
	*/
}

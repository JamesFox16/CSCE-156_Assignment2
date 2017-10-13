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
	private String name;
	//private int quantity;
	
	public Product(String productCode, String productType, double unitPrice, String name) {
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
	
	public String getName() {
		return this.name;
	}
	
}

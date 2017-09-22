
public class Product {

	private String productCode;
	private String productType;
	private double price;
	
	public Product(String productCode, String productType, double unitPrice) {
		this.productCode = productCode;
		this.productType = productType;
		this.price = unitPrice;
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
	
}

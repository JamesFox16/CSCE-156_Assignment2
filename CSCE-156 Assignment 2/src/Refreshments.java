
public class Refreshments extends Product{
	private String name;
	
	public Refreshments(String productCode, String productType, String name, double unitPrice) {
		super(productCode, productType, unitPrice, name);
		this.name = name;
	}
	
	public double computeTax() {
		return this.getProductPrice() * 0.04;
	}
	
	//Getter/Setter for name
	public String getName() {
		return this.name;
	}
	
}
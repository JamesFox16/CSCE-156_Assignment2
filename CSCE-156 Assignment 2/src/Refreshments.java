
public class Refreshments extends Product{
	private String name;
	
	public Refreshments(String productCode, String productType, String name, double unitPrice) {
		super(productCode, productType, unitPrice);
		this.name = name;
	}
	
	//Getter/Setter for name
	public String getName() {
		return this.name;
	}
	
}
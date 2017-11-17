package cinac;


public class ParkingPass extends Product{
	
	public ParkingPass(String productCode, String productType, double unitPrice) {
		super(productCode, productType, unitPrice, "Parking Pass");
	}
	
	public double computeTax() {
		return this.getProductPrice() * 0.04;
	}
	
	public String getName() {
		return "Parking Pass";
	}
}

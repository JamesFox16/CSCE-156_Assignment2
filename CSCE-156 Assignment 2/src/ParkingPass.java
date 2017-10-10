
public class ParkingPass extends Product{
	
	public ParkingPass(String productCode, String productType, double unitPrice) {
		super(productCode, productType, unitPrice);
	}
	
	public double computeTax() {
		return this.getProductPrice() * 0.04;
	}
}

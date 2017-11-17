package cinac;


public class General extends Customer {

	public General(String customerCode, String type, Person person, String name, Address address) {
		super(customerCode, type, person, name, address);
		// TODO Auto-generated constructor stub
	}
	
	public double computeTax() {
		return 1;
	}
	
	public double computeDiscount() {
		return 0;
	}
	
	public double addFees() {
		return 0;
	}
	
	public String getType() {
		return "General";
	}

}

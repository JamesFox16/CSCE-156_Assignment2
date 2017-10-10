
public class Student extends Customer {

	public Student(String customerCode, String type, Person person, String name, Address address) {
		super(customerCode, type, person, name, address);
		// TODO Auto-generated constructor stub
	}

	public double computeTax() {
		return 0;
	}
	
	public double computeDiscount() {
		return 0.08;
	}
	
	public double addFees() {
		return 6.75;
	}
	
	public String getType() {
		return "Student";
	}
	
}

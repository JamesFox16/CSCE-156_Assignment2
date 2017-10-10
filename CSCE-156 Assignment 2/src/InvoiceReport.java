
public class InvoiceReport {

	public static void main(String[] args) {
		
		//Summary Head
		System.out.printf("%-10s %-20s %-20s %-10s %-10s %-10s %-10s %-10s\n", "Invoice", "Customer", "Salesperson", "Subtotal", "Fees", "Tax", "Discount", "Total");
		
		//Summary output
		//Test/Examples below
		Person p = new Person("abc", "John", null);
		Customer c = new Student("zyx", "S", p, "José", null);
		Invoice i = new Invoice("01", c, p, "00-00-0000", null);
		
		i.summary();
		
		//Summary totals
		System.out.printf("%-60s %-10s %-20s %-20s\n", "TOTAL:", "sub", "tax", "total");
		
		//Detail
		i.detailed();
	}

}

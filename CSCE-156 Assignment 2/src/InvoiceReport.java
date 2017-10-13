import java.io.IOException;
import java.util.List;

public class InvoiceReport {

	public static void main(String[] args) throws IOException {
		
		FlatFileReader ffr = new FlatFileReader();
		List<Person> per = ffr.createPersonList();
		List<Customer> cust = ffr.createCustomerList();
		List<Product> pro = ffr.createProductList();
		List<Invoice> invoice = ffr.createInvoiceList();
		if(invoice != null) {
			System.out.println(invoice.toString());
		}else {
			System.out.println("?");
		}
		
		
		//Summary Head
		//System.out.printf("%-10s %-20s %-20s %-10s %-10s %-10s %-10s %-10s\n", "Invoice", "Customer", "Salesperson", "Subtotal", "Fees", "Tax", "Discount", "Total");
		
		//Summary output
		//Test/Examples below
		Person p = new Person("abc", "John", null);
		Customer c = new Student("zyx", "S", p, "Jose", null);
		Invoice i = new Invoice("01", c, p, "00-00-0000", null,null,null);
		
		i.summary();
		
		//Summary totals
		//System.out.printf("%-60s %-10s %-20s %-20s\n", "TOTAL:", "sub", "tax", "total");
		
		//Detail
		i.detailed();
	}

}

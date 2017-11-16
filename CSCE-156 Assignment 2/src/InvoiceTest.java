import java.io.IOException;
import java.util.List;

public class InvoiceTest {

	public static void main(String[] args) throws IOException {
		
		//Create needed lists
		FlatFileReader ffr = new FlatFileReader();
		List<Person> per = ffr.createPersonList();
		List<Customer> cust = ffr.createCustomerList();
		List<Product> pro = ffr.createProductList();
		List<Invoice> invoice = ffr.createInvoiceList();
				
		//Create LinkedList
		InvoiceList il = new InvoiceList();
		
		//Add Invoices to list
		for(int i=0; i<invoice.size(); i++) {
			il.sortedInsertion(invoice.get(i));
		}
		
		//Print list
		il.printNodes();
		
	}
	
}

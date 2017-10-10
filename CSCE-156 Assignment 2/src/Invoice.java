import java.util.List;

public class Invoice {

	//Private variables for the invoice
	private String invoiceCode;
	//private String customerCode;
	//private String salespersonCode;
	private Customer customer;
	private Person salesPerson;
	private String date;
	private List<Product> products;
	
	
	//Constructor for the invoice objects
	public Invoice(String invoiceCode, Customer customer, Person salesPerson,
					String date, List<Product> products) {
		this.invoiceCode = invoiceCode;
		//this.customerCode = customerCode;
		//this.salespersonCode = salespersonCode;
		this.customer = customer;
		this.salesPerson = salesPerson;
		this.date = date;
		this.products = products;
	}
	
	//Simple Invoice
	public void summary() {
		
		//Start of an invoice line. ToDo: calculates subtotal, tax, discount, total. Placeholders for <-- are below
		System.out.printf("%-10s %-20s %-20s %-10s %-10s %-10s %-10s %-10s\n",this.invoiceCode, this.customer.getName(), this.salesPerson.getFirstName(), "subtotal", this.customer.addFees(), "tax", "discount", "total");
		
	}
	
	//Detailed Invoice
	public void detailed() {
		
		this.customer.toString();
		
	}
	
	//Both simple and detailed together
	public void generateInvoice(Invoice i) {
		i.summary();
		i.detailed();
	}
	
	//Help Format
	@Override
	public String toString() {
		System.out.printf("Salesperson: %s\n", this.salesPerson.getFirstName());
		System.out.println("Customer info:");
		System.out.printf("%-5s \n%-5s \n%-5s \n%-5s \n", this.customer.getName(), this.customer.getType(), this.customer.getPerson().getFirstName(), this.customer.getAddress());
		return null;
	}
	
}


public class InvoiceSummary {

	private String invoice;
	private String customer;
	private String salesPerson;
	private double subtotal;
	private double fees;
	private double taxes;
	private double discount;
	private double total;
	
	
	
	public InvoiceSummary(String invoice, String customer, String salesPerson, double subtotal, double fees,
			double taxes, double discount, double total) {
		super();
		this.invoice = invoice;
		this.customer = customer;
		this.salesPerson = salesPerson;
		this.subtotal = subtotal;
		this.fees = fees;
		this.taxes = taxes;
		this.discount = discount;
		this.total = total;
	}
public void summary() {
		
		//Start of an invoice line. ToDo: calculates subtotal, tax, discount, total. Placeholders for <-- are below
		System.out.printf("%-10s %-20s %-20s %-10s %-10s %-10s %-10s %-10s\n",invoice, customer, salesPerson, subtotal, fees, tax, discount, total);
		
	}
	
}

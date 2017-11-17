package cinac;


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



	public String getInvoice() {
		return invoice;
	}



	public String getCustomer() {
		return customer;
	}



	public String getSalesPerson() {
		return salesPerson;
	}



	public double getSubtotal() {
		return subtotal;
	}



	public double getFees() {
		return fees;
	}



	public double getTaxes() {
		return taxes;
	}



	public double getDiscount() {
		return discount;
	}



	public double getTotal() {
		return total;
	}
	
	
	
}

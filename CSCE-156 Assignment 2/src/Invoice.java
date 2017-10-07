import java.util.List;

public class Invoice {

	//Private variables for the invoice
	private String invoiceCode;
	private String customerCode;
	private String salespersonCode;
	private String date;
	private List<Product> products;
	
	//Constructor for the invoice objects
	public Invoice(String invoiceCode, String customerCode, String salespersonCode,
					String date, List<Product> products) {
		this.invoiceCode = invoiceCode;
		this.customerCode = customerCode;
		this.salespersonCode = salespersonCode;
		this.date = date;
		this.products = products;
	}
	
}

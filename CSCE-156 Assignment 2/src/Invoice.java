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
	private List<String> quantityForProducts;
	
	//Constructor for the invoice objects
	public Invoice(String invoiceCode, Customer customer, Person salesPerson,
					String date, List<Product> products, List<String> quantityForProducts) {
		this.invoiceCode = invoiceCode;
		//this.customerCode = customerCode;
		//this.salespersonCode = salespersonCode;
		this.customer = customer;
		this.salesPerson = salesPerson;
		this.date = date;
		this.products = products;
		this.quantityForProducts = quantityForProducts;
	}
	
	public void calculateTotal() {
		
		Product tempProduct = products.get(0);
		double price = tempProduct.getProductPrice();
		int tempInt = Integer.parseInt(quantityForProducts.get(0));
		double tempSubTotal = price * tempInt;
		System.out.printf("%s8 %s20 %s10 %s8 %s8", "Code", "Item", "SubTotal", "Tax", "Total");
	
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
	
	private String getSalesPersonName() {
		return salesPerson.getFirstName();
	}
	
	//Help Format
	@Override
	public String toString() {
		
		System.out.printf("Salesperson: %s\n", getSalesPersonName());
		System.out.println("Customer info:");
		System.out.printf("%-5s \n%-5s \n%-5s \n%-5s \n", this.customer.getName(), 
				this.customer.getType(),
				this.customer.getPerson().getFirstName(),
				this.customer.getAddress());
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.printf("%-8s %-40s %-10s %-10s %-10s\n", "Code:", "Item:", "SubTotal:", "Tax:", "Total:");
		for(int i=0; i<products.size(); i++) {
			Product tempProduct = products.get(i);
			double price = tempProduct.getProductPrice();
			int tempInt = Integer.parseInt(quantityForProducts.get(i));
			double tempSubTotal = price * tempInt;
			System.out.printf("%-8s %-40s %-10.2f %-10s %-10.2f\n", tempProduct.getProductCode(), 
					tempProduct.getProductType(), tempSubTotal, "0", tempSubTotal);
		}
		System.out.println("-------------------------------------------------------------------------------------\n\n");
		return null;
		
	}
	
	
	
}
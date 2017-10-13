import java.text.DecimalFormat;
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
	private List<String> ticketCode;
	
	//Constructor for the invoice objects
	public Invoice(String invoiceCode, Customer customer, Person salesPerson,
					String date, List<Product> products, List<String> quantityForProducts, List<String> ticketCode) {
		this.invoiceCode = invoiceCode;
		//this.customerCode = customerCode;
		//this.salespersonCode = salespersonCode;
		this.customer = customer;
		this.salesPerson = salesPerson;
		this.date = date;
		this.products = products;
		this.quantityForProducts = quantityForProducts;
		this.ticketCode = ticketCode;
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
		System.out.printf("%-8s %-50s %-10s %-10s %-10s\n", "Code:", "Item:", "SubTotal:", "Tax:", "Total:");
		
		DecimalFormat df = new DecimalFormat("#####.##");
		double totalTotal=0;
		double totalSubTotal=0;
		double totalTax = 0;
		double taxRate;
		boolean ticketHasBeenPurchased = false;
		if(this.customer.getType().equals("S")) {
			taxRate = 0;
		}else {
			taxRate = .04;
		}
		DateDiscount dateDiscountChecker = new DateDiscount();
		for(int i=0; i<products.size(); i++) {
			
			
			Product tempProduct = products.get(i);
			double price = tempProduct.getProductPrice();
			int tempInt = Integer.parseInt(quantityForProducts.get(i));
			// Checking to see if the tax rate applys in specific cases.
			if((tempProduct.getProductType().equals("M") || tempProduct.getProductType().equals("P")) 
					&& this.customer.getType().equals("G")) {
				taxRate = .06;
			}
			if(tempProduct.getProductType().equals("M")) {
				ticketHasBeenPurchased = true;
				MovieTicket tempMovie = (MovieTicket)tempProduct;
				boolean discountTF = dateDiscountChecker.isTuOrThurs(tempMovie.getDateTime());
				if(discountTF) {
					price = price * .93;//7% discount
				}
				
				double tempSubTotal = price * tempInt;
				double tempTax = tempSubTotal * taxRate;
				double tempTotal = tempSubTotal + tempTax;
				totalTotal += tempTotal;
				totalSubTotal += tempSubTotal;
				totalTax += tempTax;
				System.out.printf("%-8s %-50s $%-10.2f $%-10.2f $%-10.2f\n", tempProduct.getProductCode(), 
						tempProduct.getName()+ " ("+tempInt+" units @ $"+ df.format(price)+ "/Unit 7% Discount for T/Th) ", tempSubTotal, 
						tempTax, tempTotal);
				
			}else if(tempProduct.getProductType().equals("R") && ticketHasBeenPurchased) {
				
				price = price * .95;//7% discount
				
				
				double tempSubTotal = price * tempInt;
				double tempTax = tempSubTotal * taxRate;
				double tempTotal = tempSubTotal + tempTax;
				totalTotal += tempTotal;
				totalSubTotal += tempSubTotal;
				totalTax += tempTax;
				System.out.printf("%-8s %-50s $%-10.2f $%-10.2f $%-10.2f\n", tempProduct.getProductCode(), 
						tempProduct.getName()+ " ("+tempInt+" units @ $"+ df.format(price)+ "/Unit - 5% discount) ", tempSubTotal, 
						tempTax, tempTotal);
			}
			
			else {
				double tempSubTotal = price * tempInt;
				double tempTax = tempSubTotal * taxRate;
				double tempTotal = tempSubTotal + tempTax;
				totalTotal += tempTotal;
				totalSubTotal += tempSubTotal;
				totalTax += tempTax;
				System.out.printf("%-8s %-50s $%-10.2f $%-10.2f $%-10.2f\n", tempProduct.getProductCode(), 
					tempProduct.getName()+ " ("+tempInt+" units @ $"+ price+ "/Unit) ", tempSubTotal, 
					tempTax, tempTotal);
			}
			
		}
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.printf("%-60s $%-10.2f $%-10.2f $%-10.2f\n", "Sub-Totals:", totalSubTotal, totalTax, totalTotal);
		
		// Output for the student discount and onetime fee.
		if (this.customer.getType().equals("S")) {
			double discount = totalTotal *.08;
			totalTotal = totalTotal - discount;
			System.out.printf("%-10s $-%-10.2f\n", "Student Discount:", discount);
			totalTotal += 6.75;
			System.out.printf("%-10s $%-10.2f\n", "One Time Student Fee:", 6.75);
		}
		
		System.out.printf("%-10s $%-10.2f\n\n\n", "TOTAL:", totalTotal);
		return null;
		
	}
	
	
	
}

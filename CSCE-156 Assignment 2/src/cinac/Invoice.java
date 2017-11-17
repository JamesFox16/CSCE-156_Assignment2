package cinac;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Invoice {

	//Private variables for the invoice
	private String invoiceCode;
	private Customer customer;
	private Person salesPerson;
	private String date;
	private List<Product> products;
	private List<String> quantityForProducts;
	private List<String> ticketCode;
	private List<InvoiceSummary> inSum = new ArrayList<InvoiceSummary>();
	private double theActualFinalTotal=0;
	private double theActualFinalTaxTotal=0;
	private double theActualFinalSubTotalTotal=0;
	private double theActualFinalFees=0;
	private double theAcutalFinalDiscount=0;
	private String customerCode;
	private String personCode;
	private String productID;
	private int quantity;
	
	//Constructor for the invoice objects
	public Invoice(String invoiceCode, Customer customer, Person salesPerson,
					String date, List<Product> products, List<String> quantityForProducts, List<String> ticketCode) {
		this.invoiceCode = invoiceCode;
		this.customer = customer;
		this.salesPerson = salesPerson;
		this.date = date;
		this.products = products;
		this.quantityForProducts = quantityForProducts;
		this.ticketCode = ticketCode;
	}
	
	public Invoice(String invoiceCode, Customer customer, Person salesPerson,
			String date, List<Product> products, List<String> quantityForProducts) {
		this.invoiceCode = invoiceCode;
		this.customer = customer;
		this.salesPerson = salesPerson;
		this.date = date;
		this.products = products;
		this.quantityForProducts = quantityForProducts;
		//System.out.println(invoiceCode+" "+date);
	}
	
	public Invoice(String invoiceCode, String customerCode, String personCode, String date, String productID, int quantity ) {
		this.invoiceCode = invoiceCode;
		this.customerCode = customerCode;
		this.personCode = personCode;
		this.date = date;
		this.productID = productID;
		this.quantity = quantity;
	}
	
	//Simple Invoice
	public void summary(int number) {
		InvoiceSummary temp = inSum.get(number);
		//Start of an invoice line
		System.out.printf("%-10s %-30s %-20s $%-10.2f $%-10.2f $%-10.2f -$%-10.2f $%-10.2f\n",temp.getInvoice(), temp.getCustomer(),
				temp.getSalesPerson(), temp.getSubtotal(), temp.getFees(), temp.getTaxes(), temp.getDiscount(), 
				temp.getTotal());
		theActualFinalTotal += temp.getTotal();
		theActualFinalTaxTotal +=temp.getTaxes() ;
		theActualFinalSubTotalTotal += temp.getSubtotal();
		theActualFinalFees += this.customer.addFees();
		theAcutalFinalDiscount += temp.getDiscount();
		
	}
	public int getQuantity() {
		return this.quantity;
	}
	
	
	private String getSalesPersonName() {
		return salesPerson.getFirstName();
	}
	
	public double getTotal() {
		return this.theActualFinalTotal;
	}
	public double getTaxTotal() {
		return this.theActualFinalTaxTotal;
	}
	public double getSubTotalTotal() {
		return this.theActualFinalSubTotalTotal;
	}
	public double getDiscountTotal() {
		return this.theAcutalFinalDiscount;
	}
	public double getFeesTotal() {
		return this.theActualFinalFees;
	}
	public String getCode() {
		return this.invoiceCode;
	}
	
	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	
	public String getProductID() {
		return this.productID;
	}

	//Help Format
	@Override
	public String toString() {
		
		String type;
		//Slightly better looking Customer Type on Report
		if(this.customer.getType().equals("S")) {
			type = "Student";
		}else {
			type = "General";
		}
		
		System.out.printf("Salesperson: %s\n", getSalesPersonName());
		System.out.println("Customer info:");
		System.out.printf("%-5s \n%-5s \n%-5s \n%-5s \n", this.customer.getName(), 
				type,
				this.customer.getPerson().getFirstName(),
				this.customer.getAddress());
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.printf("%-8s %-50s %-10s %-10s %-10s\n", "Code:", "Item:", "SubTotal:", "Tax:", "Total:");
		
		DecimalFormat df = new DecimalFormat("#####.##");
		double totalTotal=0;
		double totalSubTotal=0;
		double totalTax = 0;
		double taxRate;
		double discount = 0;
		boolean ticketHasBeenPurchased = false;
		boolean ticketParkingConnection=false;
		if(this.customer.getType().equals("S")) {
			taxRate = 0;
		}else {
			taxRate = .04;
		}
		DateDiscount dateDiscountChecker = new DateDiscount();
		for(int i=0; i<products.size(); i++) {
			
			//Check for free Parking Pass
//			if(ticketCode.get(i) != null) {
//				ticketParkingConnection = true;
//			}else {
//				ticketParkingConnection = false;
//			}
			
			Product tempProduct = products.get(i);
			double price = tempProduct.getProductPrice();
			int tempInt = Integer.parseInt(quantityForProducts.get(i));
			// Checking to see if the tax rate applys in specific cases.
			if((tempProduct.getProductType().equals("M") || tempProduct.getProductType().equals("P")) 
					&& this.customer.getType().equals("G")) {
				taxRate = .06; //Ticket tax rate
			}
			
			if(tempProduct.getProductType().equals("M")) { //Movie tickets
				ticketHasBeenPurchased = true;
				MovieTicket tempMovie = (MovieTicket)tempProduct;
				boolean discountTF = dateDiscountChecker.isTuOrThurs(tempMovie.getDateTime());
				if(discountTF) {
					price = price * .93;//7% discount for date of purchase
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
				
				price = price * .95;//5% discount for pre-ordering
				
				
				double tempSubTotal = price * tempInt;
				double tempTax = tempSubTotal * taxRate;
				double tempTotal = tempSubTotal + tempTax;
				totalTotal += tempTotal;
				totalSubTotal += tempSubTotal;
				totalTax += tempTax;
				System.out.printf("%-8s %-50s $%-10.2f $%-10.2f $%-10.2f\n", tempProduct.getProductCode(), 
						tempProduct.getName()+ " ("+tempInt+" units @ $"+ df.format(price)+ "/Unit - 5% discount) ", tempSubTotal, 
						tempTax, tempTotal);
				
			}else if(ticketParkingConnection) {//Free parking passes for ticket purchases
				
				double tempSubTotal =0;
				double tempTax = tempSubTotal * taxRate;
				double tempTotal = 0;
				totalTotal += tempTotal;
				totalSubTotal += tempSubTotal;
				totalTax += tempTax;
				System.out.printf("%-8s %-50s $%-10.2f $%-10.2f $%-10.2f\n", tempProduct.getProductCode(), 
					tempProduct.getName()+ " ("+tempInt+" units @ $0.00"+ "/Unit with Tickets) ", tempSubTotal, 
					tempTax, tempTotal);
				
			}else {
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
		
		// Output for the student discount and one time fee.
		if (this.customer.getType().equals("S")) {
			discount = totalTotal *.08;
			totalTotal = totalTotal - discount;
			System.out.printf("%-10s $-%-10.2f\n", "Student Discount:", discount);
			totalTotal += 6.75;
			System.out.printf("%-10s $%-10.2f\n", "One Time Student Fee:", 6.75);
		}
		
		System.out.printf("%-10s $%-10.2f\n\n\n", "TOTAL:", totalTotal);
		System.out.println("        Thank you for your purchase!\n\n\n");
		
		//List to be used for Summary Report
		InvoiceSummary tempSum = new InvoiceSummary(this.invoiceCode, this.customer.getName(), this.salesPerson.getFirstName(), 
				totalSubTotal, this.customer.addFees(), totalTax, discount, totalTotal);
		inSum.add(tempSum);
		
		return null;
		
	}

	public double calculateTotal() {
		
		double totalTotal=0;
		double totalSubTotal=0;
		double totalTax = 0;
		double taxRate;
		double discount = 0;
		boolean ticketHasBeenPurchased = false;
		boolean ticketParkingConnection = false;
		if(this.customer.getType().equals("S")) {
			taxRate = 0;
		}else {
			taxRate = .04;
		}
		DateDiscount dateDiscountChecker = new DateDiscount();
		for(int i=0; i<products.size(); i++) {
			
			//Check for free Parking Pass
			if(ticketCode.get(i) != null) {
				ticketParkingConnection = true;
			}else {
				ticketParkingConnection = false;
			}
			Product tempProduct = products.get(i);
			double price = tempProduct.getProductPrice();
			int tempInt = Integer.parseInt(quantityForProducts.get(i));
			// Checking to see if the tax rate applys in specific cases.
			if((tempProduct.getProductType().equals("M") || tempProduct.getProductType().equals("P")) 
					&& this.customer.getType().equals("G")) {
				taxRate = .06; //Ticket tax rate
			}
			if(tempProduct.getProductType().equals("M")) { //Movie tickets
				ticketHasBeenPurchased = true;
				MovieTicket tempMovie = (MovieTicket)tempProduct;
				boolean discountTF = dateDiscountChecker.isTuOrThurs(tempMovie.getDateTime());
				if(discountTF) {
					price = price * .93;//7% discount for date of purchase
				}
				double tempSubTotal = price * tempInt;
				double tempTax = tempSubTotal * taxRate;
				double tempTotal = tempSubTotal + tempTax;
				totalTotal += tempTotal;
				totalSubTotal += tempSubTotal;
				totalTax += tempTax;
			}else if(tempProduct.getProductType().equals("R") && ticketHasBeenPurchased) {
				price = price * .95;//5% discount for pre-ordering
				double tempSubTotal = price * tempInt;
				double tempTax = tempSubTotal * taxRate;
				double tempTotal = tempSubTotal + tempTax;
				totalTotal += tempTotal;
				totalSubTotal += tempSubTotal;
				totalTax += tempTax;
			}else if(ticketParkingConnection) {//Free parking passes for ticket purchases
				double tempSubTotal =0;
				double tempTax = tempSubTotal * taxRate;
				double tempTotal = 0;
				totalTotal += tempTotal;
				totalSubTotal += tempSubTotal;
				totalTax += tempTax;
			}else {
				double tempSubTotal = price * tempInt;
				double tempTax = tempSubTotal * taxRate;
				double tempTotal = tempSubTotal + tempTax;
				totalTotal += tempTotal;
				totalSubTotal += tempSubTotal;
				totalTax += tempTax;
			}
		}
		// Output for the student discount and one time fee.
		if (this.customer.getType().equals("S")) {
			discount = totalTotal *.08;
			totalTotal = totalTotal - discount;
			totalTotal += 6.75;
		}
		return totalTotal;
	}
	
	public void wordlessString() {
		double totalTotal=0;
		double totalSubTotal=0;
		double totalTax = 0;
		double taxRate;
		double discount = 0;
		boolean ticketHasBeenPurchased = false;
		boolean ticketParkingConnection = false;
		if(this.customer.getType().equals("S")) {
			taxRate = 0;
		}else {
			taxRate = .04;
		}
		DateDiscount dateDiscountChecker = new DateDiscount();
		for(int i=0; i<products.size(); i++) {
			
//			//Check for free Parking Pass
//			if(ticketCode.get(i) != null) {
//				ticketParkingConnection = true;
//			}else {
//				ticketParkingConnection = false;
//			}
			Product tempProduct = products.get(i);
			double price = tempProduct.getProductPrice();
			int tempInt = Integer.parseInt(quantityForProducts.get(i));
			// Checking to see if the tax rate applys in specific cases.
			if((tempProduct.getProductType().equals("M") || tempProduct.getProductType().equals("P")) 
					&& this.customer.getType().equals("G")) {
				taxRate = .06; //Ticket tax rate
			}
			if(tempProduct.getProductType().equals("M")) { //Movie tickets
				ticketHasBeenPurchased = true;
				MovieTicket tempMovie = (MovieTicket)tempProduct;
				boolean discountTF = dateDiscountChecker.isTuOrThurs(tempMovie.getDateTime());
				if(discountTF) {
					price = price * .93;//7% discount for date of purchase
				}
				double tempSubTotal = price * tempInt;
				double tempTax = tempSubTotal * taxRate;
				double tempTotal = tempSubTotal + tempTax;
				totalTotal += tempTotal;
				totalSubTotal += tempSubTotal;
				totalTax += tempTax;
			}else if(tempProduct.getProductType().equals("R") && ticketHasBeenPurchased) {
				price = price * .95;//5% discount for pre-ordering
				double tempSubTotal = price * tempInt;
				double tempTax = tempSubTotal * taxRate;
				double tempTotal = tempSubTotal + tempTax;
				totalTotal += tempTotal;
				totalSubTotal += tempSubTotal;
				totalTax += tempTax;
			}else if(ticketParkingConnection) {//Free parking passes for ticket purchases
				double tempSubTotal =0;
				double tempTax = tempSubTotal * taxRate;
				double tempTotal = 0;
				totalTotal += tempTotal;
				totalSubTotal += tempSubTotal;
				totalTax += tempTax;
			}else {
				double tempSubTotal = price * tempInt;
				double tempTax = tempSubTotal * taxRate;
				double tempTotal = tempSubTotal + tempTax;
				totalTotal += tempTotal;
				totalSubTotal += tempSubTotal;
				totalTax += tempTax;
			}
		}
		// Output for the student discount and one time fee.
		if (this.customer.getType().equals("S")) {
			discount = totalTotal *.08;
			totalTotal = totalTotal - discount;
			totalTotal += 6.75;
		}
		//List to be used for Summary Report
				InvoiceSummary tempSum = new InvoiceSummary(this.invoiceCode, this.customer.getName(), this.salesPerson.getFirstName(), 
						totalSubTotal, this.customer.addFees(), totalTax, discount, totalTotal);
				inSum.add(tempSum);
	}
}

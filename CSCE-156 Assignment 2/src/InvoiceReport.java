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
			invoice.toString();
		}else {
			System.out.println("?");
		}
		
		
		System.out.println("===================================");
		System.out.println("Executive Summary Report");
		System.out.println("=========================");
		//Summary Head
		System.out.printf("%-10s %-30s %-20s %-10s %-10s %-10s %-10s %-10s\n", "Invoice", "Customer", "Salesperson", "Subtotal", "Fees", "Tax", "Discount", "Total");

		for(int i=0; i<invoice.size(); i++) {
			invoice.get(i).summary(0);
		}
		double totalTotal = 0;
		double totalTax=0;
		double totalSubTotal=0;
		double totalFees=0;
		double totalDiscount =0;
		for(int i=0; i<invoice.size(); i++) {
			totalTotal += invoice.get(i).getTotal();
			totalTax += invoice.get(i).getTaxTotal();
			totalSubTotal += invoice.get(i).getSubTotalTotal();
			totalFees += invoice.get(i).getFeesTotal();
			totalDiscount += invoice.get(i).getDiscountTotal();
			
		}
		Invoice invoices = new Invoice();
		//Summary totals
		System.out.println("=====================================================================================================================");
		System.out.printf("%-60s $%-10.2f $%-10.2f $%-10.2f $%-10.2f $%-10.2f\n", "TOTAL:", totalSubTotal, totalFees,
				totalTax, totalDiscount, totalTotal);
	}

}

package cinac;

import java.io.IOException;
import java.util.*;

import com.ceg.ext.InvoiceData;

public class InvoiceReport {

	public static void execute() {
		
		List<Invoice> invoice = InvoiceData.getInvoiceList();
		List<Invoice> inv2 = new ArrayList<Invoice>();
		
		InvoiceList il = new InvoiceList();
		
		invoice.get(1).wordlessString();
		
		//Add Invoices to list
		for(int i=0; i<invoice.size(); i++) {
			il.sortedInsertion(invoice.get(i));
		}
		for(int i=2;i<il.getSize()+1;i++) {
			inv2.add(il.getInvoice(i));
		}
		il.printNodes();
		
		//Display Detailed Reports
		inv2.toString();
		
		
		
		System.out.println("===================================");
		System.out.println("Executive Summary Report");
		System.out.println("=========================");
		//Summary Head
		System.out.printf("%-10s %-30s %-20s %-10s %-10s %-10s %-10s %-10s\n", "Invoice", "Customer", "Salesperson", "Subtotal", "Fees", "Tax", "Discount", "Total");

		for(int i=2; i<=il.getSize(); i++) {
			il.getInvoice(i).summary(0);
		}
		double totalTotal=0;
		double totalTax=0;
		double totalSubTotal=0;
		double totalFees=0;
		double totalDiscount=0;
		
		//Calculate totals
		for(int i=0; i<invoice.size(); i++) {
			totalTotal += invoice.get(i).getTotal();
			totalTax += invoice.get(i).getTaxTotal();
			totalSubTotal += invoice.get(i).getSubTotalTotal();
			totalFees += invoice.get(i).getFeesTotal();
			totalDiscount += invoice.get(i).getDiscountTotal();
			
		}
		
		//Display Summary reports
		System.out.println("=====================================================================================================================");
		System.out.printf("%-60s $%-10.2f $%-10.2f $%-10.2f -$%-10.2f $%-10.2f\n", "TOTAL:", totalSubTotal, totalFees,
				totalTax, totalDiscount, totalTotal);
	
	}
	
}

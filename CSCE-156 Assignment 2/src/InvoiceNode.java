
public class InvoiceNode {
	
	private InvoiceNode next;
	private Invoice node;
	
	public InvoiceNode(Invoice i) {
		this.node = i;
		this.next = null;
	}
	
	public InvoiceNode(Invoice i, InvoiceNode next) {
		this.node = i;
		this.next = next;
	}
	
	public Invoice getInvoice(){
		return node;
	}
	
	public InvoiceNode getNext() {
		return next;
	}
	
	public void setNext(InvoiceNode next) {
		this.next = next;
	}
	
	public void setNode(Invoice i) {
		this.node = i;
	}
	
	public boolean hasNext() {
		if(this.next==null) {
			return false;
		}else {
			return true;
		}
	}
	
}

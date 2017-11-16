
public class InvoiceList {

	private InvoiceNode start;
	private InvoiceNode end;
	private int size;
	private TotalComparator comp;
	
	public InvoiceList() {
		start = null;
		end = null;
		size = 0;
	}
	
	public void clear() {
		start = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return start==null;
	}
	
	public int getSize() {
		return size;
	}
	
	public void insertStart(Invoice i) {
		InvoiceNode node = new InvoiceNode(i);
		size++;
		if(start==null) {
			start = node;
			end = start;
		}else {
			node.setNext(start);
			start = node;
		}
	}
	
	public void insertEnd(Invoice i) {
		InvoiceNode node = new InvoiceNode(i);
		size++;
		if(start==null) {
			this.insertStart(i);
		}else {
			end.setNext(node);
			end = node;
		}
	}
		
	public boolean insertNode(Invoice i, int index) {
		InvoiceNode node = new InvoiceNode(i);
		InvoiceNode previous = start;
		InvoiceNode next;
		if(size==1) {
			this.insertStart(i);
			return true;
		}else if(index <= size) {
			for(int j=1; j<(index-1); j++) {
				if(previous.getNext()==null) {
					return false;
				}
			previous = previous.getNext();
			}
			next = previous.getNext();
			previous.setNext(node);
			node.setNext(next);
			size++;
			return true;
		}else if(index==size) {
			this.insertEnd(i);
			return true;
		}else {
			System.out.println("Out of bounds error");
			return false;
		}
	}
	
	public boolean removeNode(int index) {
		if(index > size) {
			return false;
		}
		InvoiceNode remove;
		InvoiceNode node = start;
		InvoiceNode next;
		if(index==1) {
			start = start.getNext();
			size--;
			return true;
		}else {
			for(int j=1; j<(index-1); j++) {
				if(node.getNext()==null) {
					return false;
				}
				node = node.getNext();
			}
			if(index==size) {
				node.setNext(null);
				end = node;
				size--;
				return true;
			}else {
				remove = node.getNext();
				next = remove.getNext();
				node.setNext(next);
				size--;
				return true;
			}
		}
	}
	
	public void sortedInsertion(Invoice i) {//Currently sorts by total
		if(start==null) {
			this.insertStart(i);
		}
		InvoiceNode node = start;
		if(i.calculateTotal() > start.getInvoice().calculateTotal()) {
			this.insertStart(i);
			return;
		}
		int j=1;
		while(node.hasNext()) {
			if(i.calculateTotal() > node.getInvoice().calculateTotal()) {
				this.insertNode(i, j);
				return;
			}
			j++;
			node = node.getNext();
		}
		this.insertEnd(i);
		return;
	}
	
	public void printNodes() {
		InvoiceNode node = start;
		for(int i=0; i<(size-1); i++) {
			System.out.println(node.getInvoice().getCode());
			node = node.getNext();
		}
	}
	
}

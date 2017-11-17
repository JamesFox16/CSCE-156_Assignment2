package cinac;

import java.util.Comparator;

public class TotalComparator implements Comparator<Invoice> {

	@Override
	public int compare(Invoice i1, Invoice i2) {
		if(i1.calculateTotal() == i2.calculateTotal()) {
			return 0;
		}else if(i1.calculateTotal() > i2.calculateTotal()) {
			return 1;
		}else
			return -1;
	}
	
}

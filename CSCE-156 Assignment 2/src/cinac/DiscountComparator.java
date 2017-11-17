package cinac;

import java.util.Comparator;

public class DiscountComparator implements Comparator<Invoice> {
	
	@Override
	public int compare(Invoice i1, Invoice i2) {
		if(i1.getDiscountTotal() == i2.getDiscountTotal()) {
			return 0;
		}else if(i1.getDiscountTotal() > i2.getDiscountTotal()) {
			return 1;
		}else {
			return -1;
		}
	}

}

import java.util.Calendar;

public class DateDiscount {

	public DateDiscount() {
		
	}
	
	public boolean isTuOrThurs(String date) {
		String[] dates = date.split("-");
		String[] daySplit = dates[2].split(" ");
		int year = Integer.parseInt(dates[0]);
		int month = Integer.parseInt(dates[1]);
		int day = Integer.parseInt(daySplit[0]);
		
		Calendar c = Calendar.getInstance();
		c.set(year, month, day);
		
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 3 || dayOfWeek == 5) {
			return true;
		}else {
			return false;
		}
	}
	
}

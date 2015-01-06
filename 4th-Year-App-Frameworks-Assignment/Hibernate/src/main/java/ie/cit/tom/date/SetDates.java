package ie.cit.tom.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SetDates {
	
	public Date getLoanDate() {
		String date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		
		return convertStringToDate(date);
	}
	public Date getReturnDate() {
		Calendar now = Calendar.getInstance();
		// this line will add two weeks onto the loan date
	    now.add(Calendar.WEEK_OF_YEAR, 2);
	    String date = now.get(Calendar.DATE) + "/" + (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.YEAR);
	    //String date = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DATE);
		return convertStringToDate(date);
	}
	private Date convertStringToDate(String str_date) {
	    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = format.parse(str_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
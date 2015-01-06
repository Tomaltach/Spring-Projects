package ie.cit.tom.date;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class CompareDates {
	private static final double FINE_PER_DAY = 0.05; 
	
	public BigDecimal getFine(Date date) {
		String str_date = date.toString();
		double fine = roundTwoDecimals(checkReturnDate(str_date));
		
		return (new BigDecimal(fine));
	}
	public String getReturnDate() {
		Calendar now = Calendar.getInstance();
		// this line will add two weeks onto the loan date
	    now.add(Calendar.WEEK_OF_YEAR, 2);
	    String return_date = now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DATE);
	    return return_date;
	}
	public Double checkReturnDate(String return_dateIn) {
		// this method will get the return date and calculate the fine if the number of days difference is greater than 0
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date return_date = null;
		try {
			return_date = format.parse(return_dateIn);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String today = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		int days = Days.daysBetween(new DateTime(return_date), new DateTime(today)).getDays();
		if(days > 0) {
			return calculateFine(days);
		}
		return 0.0;
	}
	public Double calculateFine(int days) {
		Double fine = days * FINE_PER_DAY;
		return fine;
	}
	public Double roundTwoDecimals(double fine) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(fine));
	}
}

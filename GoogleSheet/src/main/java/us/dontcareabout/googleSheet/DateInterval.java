package us.dontcareabout.googleSheet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateInterval {
	Date start;
	Date end;

	public DateInterval(Date start, Date end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String strStart = (start == null ? null : dateFormat.format(start));
		String strEnd = (end == null ? null : dateFormat.format(end));

		return String.format("(%s, %s)", strStart, strEnd);
	}
}

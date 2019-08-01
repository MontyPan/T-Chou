package us.dontcareabout.googleSheet;

import us.dontcareabout.googleSheet.Exceptions.DateIntervalErrorException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateInterval {
	Date start;
	Date end;

	public DateInterval(Date start, Date end) {
		if (start.getTime() >= end.getTime()) {
			throw new DateIntervalErrorException("Start date is not earlier than end date");
		}
		this.start = start;
		this.end = end;
	}

	public boolean containInterval(DateInterval innerInterval) {
		if (start.getTime() > innerInterval.start.getTime()) {
			return false;
		}
		if (end.getTime() < innerInterval.end.getTime()) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String strStart = (start == null ? null : dateFormat.format(start));
		String strEnd = (end == null ? null : dateFormat.format(end));

		return String.format("(%s, %s)", strStart, strEnd);
	}
}

package us.dontcareabout.googleSheet2.Exceptions;

import us.dontcareabout.googleSheet2.DateInterval;

public class DateIntervalException extends RuntimeException {
	public final DateInterval dateInterval;

	public DateIntervalException(DateInterval dateInterval) {
		super(dateInterval + " is not between intervals!");
		this.dateInterval = dateInterval;
	}
}

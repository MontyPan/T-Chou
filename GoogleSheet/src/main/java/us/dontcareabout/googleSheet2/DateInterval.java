package us.dontcareabout.googleSheet2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateInterval implements Comparable<DateInterval> {
	private Date start;
	private Date end;

	public DateInterval(Date date1, Date date2) {
		this.start = date1.before(date2) ? date1 : date2;
		this.end = date1.after(date2) ? date1 : date2;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public boolean containInterval(DateInterval innerInterval) {
		if (start.after(innerInterval.getStart())) {
			return false;
		}
		if (end.before(innerInterval.getEnd())) {
			return false;
		}
		return true;
	}

	/**
	 * 移動日期
	 *
	 * @param date 移動前的日期
	 * @param days 移動天數
	 */
	public static Date shiftDate(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String strStart = (start == null ? null : dateFormat.format(start));
		String strEnd = (end == null ? null : dateFormat.format(end));

		return String.format("(%s, %s)", strStart, strEnd);
	}

	public int compareTo(DateInterval dt) {
		return this.start.after(dt.getEnd()) ? 1 : -1;
	}
}

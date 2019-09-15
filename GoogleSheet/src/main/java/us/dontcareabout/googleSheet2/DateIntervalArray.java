package us.dontcareabout.googleSheet2;

import us.dontcareabout.googleSheet2.Exceptions.DateIntervalException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class DateIntervalArray {
	private ArrayList<DateInterval> intervals;

	public DateIntervalArray() {
		this.intervals = new ArrayList<DateInterval>();
	}

	public DateIntervalArray(List<DateInterval> intervals) {
		this.intervals = new ArrayList<DateInterval>();
		for (DateInterval d : intervals) {
			this.addInterval(d);
		}
	}

	public void addInterval(Date start, Date end) {
		intervals.add(new DateInterval(start, end));
	}

	public void addInterval(DateInterval d) {
		intervals.add(new DateInterval(d.getStart(), d.getEnd()));
	}

	/**
	 * 移除 intervals 內的一段日期
	 */
	public void cutInterval(DateInterval d) {
		DateInterval newInterval = null;
		for (DateInterval d2 : intervals) {
			if (d2.containInterval(d)) {
				newInterval = new DateInterval(shiftDate(d.getEnd(), 1), d2.getEnd());
				d2.setEnd(shiftDate(d.getStart(), -1));
				break;
			}
		}

		if (newInterval == null) throw new DateIntervalException(d + " is not between intervals");
		addInterval(newInterval);
	}

	/**
	 * @return intervals 的整段範圍
	 */
	public DateInterval getOverallInterval() {
		intervals.sort(new Comparator<DateInterval>() {
			/**
			 * 比較兩個 DateInterval 的 start。
			 */
			@Override
			public int compare(DateInterval d1, DateInterval d2) {
				return d1.getStart().compareTo(d2.getStart());
			}
		});
		return new DateInterval(intervals.get(0).getStart(), intervals.get(intervals.size() - 1).getEnd());
	}

	public List<DateInterval> getIntervals() {
		return Collections.unmodifiableList(intervals);
	}

	/**
	 * 移動日期
	 *
	 * @param date 移動前的日期
	 * @param days 移動天數
	 */
	private static Date shiftDate(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

	@Override
	public String toString() {
		return String.format("%s", intervals);
	}
}

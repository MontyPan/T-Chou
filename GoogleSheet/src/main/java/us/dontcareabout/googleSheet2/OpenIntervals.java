package us.dontcareabout.googleSheet2;

import us.dontcareabout.googleSheet2.Exceptions.DateIntervalException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class OpenIntervals {
	private ArrayList<DateInterval> intervals;

	public OpenIntervals() {
		this.intervals = new ArrayList<DateInterval>();
	}

	public OpenIntervals(List<DateInterval> intervals) {
		this.intervals = new ArrayList<DateInterval>();
		for (DateInterval d : intervals) {
			this.addOpenInterval(d);
		}
	}

	/**
	 * 加入開放期間
	 */
	public void addOpenInterval(Date start, Date end) {
		intervals.add(new DateInterval(start, end));
	}

	/**
	 * 加入開放期間
	 */
	public void addOpenInterval(DateInterval d) {
		this.addOpenInterval(d.getStart(), d.getEnd());
	}

	/**
	 * 移除換展關閉期間
	 */
	public void removeCloseInterval(DateInterval d) {
		DateInterval newInterval = null;
		for (DateInterval d2 : intervals) {
			if (d2.containInterval(d)) {
				newInterval = new DateInterval(shiftDate(d.getEnd(), 1), d2.getEnd());
				d2.setEnd(shiftDate(d.getStart(), -1));
				break;
			}
		}

		if (newInterval == null) throw new DateIntervalException(d);
		addOpenInterval(newInterval);
	}

	/**
	 * @return 開放期間的範圍
	 */
	public DateInterval getOpenRange() {
		intervals.sort(new Comparator<DateInterval>() {
			/**
			 * 比較兩個 {@link DateInterval} 的開始日期。
			 */
			@Override
			public int compare(DateInterval d1, DateInterval d2) {
				return d1.getStart().compareTo(d2.getStart());
			}
		});
		return new DateInterval(intervals.get(0).getStart(), intervals.get(intervals.size() - 1).getEnd());
	}

	/**
	 * @return 所有開放期間
	 */
	public List<DateInterval> getOpenIntervals() {
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

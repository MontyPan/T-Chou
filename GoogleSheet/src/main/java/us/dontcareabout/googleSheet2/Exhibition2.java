package us.dontcareabout.googleSheet2;

import us.dontcareabout.googleSheet2.Exceptions.DateIntervalException;
import us.dontcareabout.googleSheet2.Exceptions.ExihibitionNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Exhibition2 {
	private String name;
	private Map<String, ArrayList<DateInterval>> openIntervals = new HashMap<String, ArrayList<DateInterval>>();

	public Exhibition2(RawData data) {
		this.name = data.name;

		for (String room : data.rooms.split(",")) {
			String r = room.trim().toUpperCase();
			openIntervals.put(r, new ArrayList<DateInterval>());
			openIntervals.get(r).add(new DateInterval(data.start, data.end));
		}
	}

	/**
	 * 新增換展件關閉展廳資訊
	 *
	 * @return true 當成功加入關閉展廳資訊
	 */
	public boolean addCloseInfo(RawData data) {
		if (!this.name.equals(data.name)) return false;
		DateInterval closeInterval = new DateInterval(data.start, data.end);

		for (String room : data.rooms.split(",")) {
			String r = room.trim().toUpperCase();
			if (openIntervals.get(r) == null) {
				splitRoom(r.substring(0, r.length() - 1));
			}

			DateInterval newInterval = null;

			for (DateInterval d : openIntervals.get(r)) {
				if (d.containInterval(closeInterval)) {
					newInterval = new DateInterval(DateInterval.shiftDate(closeInterval.getEnd(), 1), d.getEnd());
					d.setEnd(DateInterval.shiftDate(closeInterval.getStart(), -1));
					break;
				}
			}

			if (newInterval == null) throw new DateIntervalException(closeInterval + " is not between open intervals");

			openIntervals.get(r).add(newInterval);
		}
		return true;
	}

	public void splitRoom(String room) {
		if (openIntervals.get(room) == null) throw new ExihibitionNotFoundException(room + " not found.");

		ArrayList<DateInterval> intervals = openIntervals.get(room);

		for (String r : ShowRoom.roomAsList(room)) {
			openIntervals.put(r, new ArrayList<DateInterval>());
			for (DateInterval di : intervals) {
				openIntervals.get(r).add(new DateInterval(di.getStart(), di.getEnd()));
			}
		}

		openIntervals.remove(room);
	}

	public String getName() {
		return name;
	}

	public Map<String, ArrayList<DateInterval>> getOpenIntervals() {
		return openIntervals;
	}

	@Override
	public String toString() {
		return String.format("Name: %s\nOpen intervals:\n%s\n", name, openIntervals);
	}
}

package us.dontcareabout.googleSheet2;

import us.dontcareabout.googleSheet2.Exceptions.DateIntervalException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Exhibition2 {
	private String name;
	private Map<String, ArrayList<DateInterval>> openIntervals = new HashMap<String, ArrayList<DateInterval>>();

	public Exhibition2(RawData data) {
		this.name = data.name;

		for (String r : ShowRoom.roomAsList(data.rooms)) {
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

		for (String r : ShowRoom.roomAsList(data.rooms)) {
			DateInterval newInterval = null;

			for (DateInterval d : openIntervals.get(r)) {
				if (d.containInterval(closeInterval)) {
					newInterval = new DateInterval(closeInterval.getEnd(), d.getEnd());
					d.setEnd(closeInterval.getStart());
					break;
				}
			}

			if (newInterval == null) throw new DateIntervalException(closeInterval + " is not between open intervals");

			openIntervals.get(r).add(newInterval);
		}
		return true;
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

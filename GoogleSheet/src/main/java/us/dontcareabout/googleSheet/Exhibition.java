package us.dontcareabout.googleSheet;

import us.dontcareabout.googleSheet.Exceptions.DateIntervalException;

import java.util.HashMap;
import java.util.Map;

public class Exhibition {
	private String name;
	private DateInterval displayDate;
	private String rooms;
	private Map<String, DateInterval> closeIntervals = new HashMap<String, DateInterval>();

	public Exhibition(RawData data) {
		this.name = data.name;
		this.displayDate = new DateInterval(data.start, data.end);
		rooms = data.rooms;
	}

	public String getName() {
		return name;
	}

	public DateInterval getDisplayDate() {
		return displayDate;
	}

	public String getRooms() {
		return rooms;
	}

	public Map<String, DateInterval> getCloseIntervals() {
		return closeIntervals;
	}

	/**
	 * 新增換展資訊
	 */
	public void addChangeInfo(RawData data) {

		DateInterval changeInterval = new DateInterval(data.start, data.end);
		if (!displayDate.containInterval(changeInterval)) {
			throw new DateIntervalException(changeInterval + " is not between " + displayDate);
		}
		closeIntervals.put(data.rooms, new DateInterval(data.start, data.end));

	}

	/**
	 * 換展次數
	 */
	public int getChangeTime() {
		return closeIntervals.keySet().size();
	}


	@Override
	public String toString() {
		return String.format("Exhibition:\nName: %s\nDate: %s\nLocation: %s\nChangeInfo (%s):\n%s\n", name, displayDate, rooms, getChangeTime(), closeIntervals);
	}
}

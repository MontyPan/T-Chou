package us.dontcareabout.googleSheet;

import us.dontcareabout.googleSheet.Exceptions.DateIntervalErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exhibition {
	String name;
	DateInterval displayDate;
	List<String> rooms = new ArrayList<String>();
	Map<String, DateInterval> closeIntervals = new HashMap<String, DateInterval>();

	public Exhibition(RawData data) {
		this.name = data.name;
		this.displayDate = new DateInterval(data.start, data.end);
		rooms.addAll(ShowRoom.roomAsList(data.rooms));
	}

	/**
	 * 新增換展資訊
	 */
	public void addChangeInfo(RawData data) {
		for (String r : ShowRoom.roomAsList(data.rooms)) {
			DateInterval changeInterval = new DateInterval(data.start, data.end);
			if (!displayDate.containInterval(changeInterval)) {
				throw new DateIntervalErrorException(changeInterval + " is not between " + displayDate);
			}
			closeIntervals.put(r, new DateInterval(data.start, data.end));
		}
	}


	@Override
	public String toString() {
		return String.format("Exhibition:\nName: %s\nDate: %s\nLocation: %s\nChangeInfo:\n%s\n", name, displayDate, rooms, closeIntervals);
	}
}

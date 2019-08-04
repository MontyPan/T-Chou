package us.dontcareabout.googleSheet;

import us.dontcareabout.googleSheet.Exceptions.DateIntervalErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exhibition {
	private String name;
	private DateInterval displayDate;
	private List<String> rooms = new ArrayList<String>();
	private Map<String, DateInterval> closeIntervals = new HashMap<String, DateInterval>();

	public Exhibition(RawData data) {
		this.name = data.name;
		this.displayDate = new DateInterval(data.start, data.end);
		rooms.addAll(ShowRoom.roomAsList(data.rooms));
	}

	public String getName() {
		return name;
	}

	public DateInterval getDisplayDate() {
		return displayDate;
	}

	public List<String> getRooms() {
		return rooms;
	}

	public Map<String, DateInterval> getCloseIntervals() {
		return closeIntervals;
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

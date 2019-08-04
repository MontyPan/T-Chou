package us.dontcareabout.googleSheet;

import us.dontcareabout.googleSheet.Exceptions.DateIntervalErrorException;

import java.util.ArrayList;
import java.util.Arrays;
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
		rooms.addAll(roomAsList(data.rooms));
	}

	/**
	 * 新增換展資訊
	 */
	public void addChangeInfo(RawData data) {
		for (String r : roomAsList(data.rooms)) {
			DateInterval changeInterval = new DateInterval(data.start, data.end);
			if (!displayDate.containInterval(changeInterval)) {
				throw new DateIntervalErrorException(changeInterval + " is not between " + displayDate);
			}
			closeIntervals.put(r, new DateInterval(data.start, data.end));
		}
	}

	/**
	 * 將展廳資料轉為 List，並以半個展廳為最小單位。
	 */
	public static List<String> roomAsList(String rooms) {
		List<String> showRooms = new ArrayList<String>();

		for (String r : rooms.split(",")) {

			char subRoom = r.charAt(r.length() - 1);
			if (subRoom == 'A' || subRoom == 'B') {
				showRooms.add(r);
				break;
			}
			showRooms.addAll(Arrays.asList(r + "A", r + "B"));
		}
		return showRooms;
	}

	@Override
	public String toString() {
		return String.format("Exhibition:\nName: %s\nDate: %s\nLocation: %s\nChangeInfo:\n%s\n", name, displayDate, rooms, closeIntervals);
	}
}

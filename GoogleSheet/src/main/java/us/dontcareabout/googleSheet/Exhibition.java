package us.dontcareabout.googleSheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exhibition {
	String name;
	Date start;
	Date end;
	List<String> rooms = new ArrayList<String>();
	Map<String, DateInterval> closeIntervals = new HashMap<String, DateInterval>();

	public Exhibition(RawData data) {
		this.name = data.name;
		this.start = data.start;
		this.end = data.end;
		rooms.addAll(roomAsList(data.rooms));
	}

	/**
	 * 新增換展資訊
	 */
	public void addChangeInfo(RawData data) {
		for (String r : roomAsList(data.rooms)) {
			closeIntervals.put(r, new DateInterval(data.start, data.end));
		}
	}

	/**
	 * 將展廳資料轉為 List，並以半個展廳為最小單位。
	 */
	private static List<String> roomAsList(String rooms) {
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
		return String.format("Exhibition:\nName: %s\nDate: %s ~ %s\nLocation: %s\nChangeInfo:\n%s\n", name, start, end, rooms, closeIntervals);
	}
}

package us.dontcareabout.googleSheet;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ShowRoom {
	String name;
	Map<String, DateInterval> open = new HashMap<String, DateInterval>();
	Map<String, DateInterval> close = new HashMap<String, DateInterval>();

	public ShowRoom(String name) {
		this.name = name;
	}

	public void addOpen(String exhibition, Date start, Date end) {
		open.put(exhibition, new DateInterval(start, end));
	}

	public void addClose(String exhibition, Date start, Date end) {
		close.put(exhibition, new DateInterval(start, end));
	}

	/**
	 * 一口氣建立所有展廳
	 */
	public static Map<String, ShowRoom> createShowRooms(String[] rooms) {
		Map<String, ShowRoom> showRoomMap = new HashMap<String, ShowRoom>();

		for (String r : rooms) {
			for (String subRoom : Exhibition.roomAsList(r)) {
				showRoomMap.put(subRoom, new ShowRoom(subRoom));
			}
		}
		return showRoomMap;
	}

	@Override
	public String toString() {
		return String.format("Room: %s\nOpen: %s\nClose: %s\n", name, open, close);
	}
}

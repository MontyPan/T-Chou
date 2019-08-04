package us.dontcareabout.googleSheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
			for (String subRoom : roomAsList(r)) {
				showRoomMap.put(subRoom, new ShowRoom(subRoom));
			}
		}
		return showRoomMap;
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
		return String.format("Room: %s\nOpen: %s\nClose: %s\n", name, open, close);
	}
}

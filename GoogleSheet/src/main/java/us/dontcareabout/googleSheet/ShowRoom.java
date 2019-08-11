package us.dontcareabout.googleSheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ShowRoom {
	S101A("S101"), S101B("S101"),
	S102A("S102"), S102B("S102"),
	S201A("S201"), S201B("S201"),
	S202A("S202"), S202B("S202"),
	S203A("S203"), S203B("S203"),
	S204A("S204"), S204B("S204"),
	S301A("S301"), S301B("S301"),
	S302A("S302"), S302B("S302"),
	S303A("S303"), S303B("S303"),
	S304A("S304"), S304B("S304");


	private String parent;
	private Map<String, DateInterval> open = new HashMap<String, DateInterval>();
	private Map<String, DateInterval> close = new HashMap<String, DateInterval>();

	ShowRoom(String parent) {
		this.parent = parent;
	}

	public String getParent() {
		return parent;
	}

	public Map<String, DateInterval> getOpen() {
		return open;
	}

	public Map<String, DateInterval> getClose() {
		return close;
	}

	public void addOpen(String exhibition, Date start, Date end) {
		open.put(exhibition, new DateInterval(start, end));
	}

	public void addClose(String exhibition, Date start, Date end) {
		close.put(exhibition, new DateInterval(start, end));
	}

	/**
	 * 將展廳資料轉為 List，並以半個展廳為最小單位。
	 */
	public static List<String> roomAsList(String rooms) {
		List<String> showRooms = new ArrayList<String>();
		String a = "A";
		String b = "B";

		for (String r : rooms.split(",")) {
			r = r.trim().toUpperCase();
			if (r.endsWith(a) || r.endsWith(b)) {
				showRooms.add(r);
				break;
			}
			showRooms.addAll(Arrays.asList(r + a, r + b));
		}
		return showRooms;
	}

	@Override
	public String toString() {
		return String.format("Room: %s\nOpen: %s\nClose: %s\n", name(), open, close);
	}
}

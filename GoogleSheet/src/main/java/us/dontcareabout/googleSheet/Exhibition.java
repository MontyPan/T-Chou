package us.dontcareabout.googleSheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Exhibition {
	String name;
	Date start;
	Date end;
	List<String> rooms;
	List<CloseInterval> closeIntervals = new ArrayList<CloseInterval>();

	public Exhibition(RawData data) {
		this.name = data.name;
		this.start = data.start;
		this.end = data.end;
		this.rooms = Arrays.asList(data.rooms.split(","));
	}

	/**
	 * 新增換展資訊
	 */
	public void addChangeInfo(RawData data) {
		closeIntervals.add(new CloseInterval(data));
	}

	@Override
	public String toString() {
		return String.format("Exhibition:\nName: %s\nDate: %s ~ %s\nLocation: %s\nChangeInfo:\n%s\n", name, start, end, rooms, closeIntervals);
	}

	private class CloseInterval {
		Date start;
		Date end;
		String rooms;

		CloseInterval(RawData data) {
			this.start = data.start;
			this.end = data.end;
			this.rooms = data.rooms;
		}

		@Override
		public String toString() {
			return String.format("(%s ~ %s @%s)\n", start, end, rooms);
		}
	}
}

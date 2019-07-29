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

	private class CloseInterval {
		Date start;
		Date end;
		String rooms;
	}
}

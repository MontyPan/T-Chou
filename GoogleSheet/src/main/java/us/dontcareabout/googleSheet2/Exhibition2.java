package us.dontcareabout.googleSheet2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Exhibition2 {
	private String name;
	private Map<String, ArrayList<DateInterval>> openIntervals = new HashMap<String, ArrayList<DateInterval>>();

	public Exhibition2(RawData data) {
		this.name = data.name;

		for (String r : ShowRoom.roomAsList(data.rooms)) {
			openIntervals.put(r, new ArrayList<DateInterval>());
			openIntervals.get(r).add(new DateInterval(data.start, data.end));
		}
	}

	@Override
	public String toString() {
		return String.format("Name: %s\nOpen intervals:\n%s\n", name, openIntervals);
	}
}

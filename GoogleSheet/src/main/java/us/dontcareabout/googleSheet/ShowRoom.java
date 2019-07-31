package us.dontcareabout.googleSheet;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowRoom {
	String name;
	Map<String, List> open = new HashMap<String, List>();
	Map<String, List> close = new HashMap<String, List>();

	public ShowRoom(String name) {
		this.name = name;
	}

	public void addOpen(String exhibition, Date start, Date end) {
		open.put(exhibition, Arrays.asList(start, end));
	}

	public void addClose(String exhibition, Date start, Date end) {
		close.put(exhibition, Arrays.asList(start, end));
	}

	@Override
	public String toString() {
		return String.format("Room: %s\nOpen: %s\nClose: %s\n", name, open, close);
	}
}

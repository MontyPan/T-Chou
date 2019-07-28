package us.dontcareabout.googleSheet;

import java.util.Date;

public class RawData {
	String name;
	boolean close;
	Date start;
	Date end;
	String rooms;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isClose() {
		return close;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getRooms() {
		return rooms;
	}

	public void setRooms(String rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return String.format("Foo [name=%s, close=%s, start=%s, end=%s, rooms=%s]", name, close, start, end, rooms);
	}
}

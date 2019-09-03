package us.dontcareabout.googleSheet;

import us.dontcareabout.googleSheet.Exceptions.DateIntervalException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Exhibition {
	private String name;
	private DateInterval displayDate;
	private int closeCount = 0;
	private Map<ShowRoom, ArrayList<DateInterval>> openIntervals = new HashMap<ShowRoom, ArrayList<DateInterval>>();

	public Exhibition(RawData data) {
		this.name = data.name;
		this.displayDate = new DateInterval(data.start, data.end);

		for (String r : ShowRoom.roomAsList(data.rooms)) {
			openIntervals.put(ShowRoom.valueOf(r), new ArrayList<DateInterval>());
			openIntervals.get(ShowRoom.valueOf(r)).add(new DateInterval(data.start, data.end));
		}
	}

	public String getName() {
		return name;
	}

	public DateInterval getDisplayDate() {
		return displayDate;
	}

	public Set<ShowRoom> getRooms() {
		return openIntervals.keySet();
	}

	public Map<ShowRoom, ArrayList<DateInterval>> getOpenIntervals() {
		return openIntervals;
	}

	/**
	 * 新增換展件關閉展廳資訊
	 *
	 * @return true 當成功加入關閉展廳資訊
	 */
	public boolean addCloseInfo(RawData data) {
		if (!data.name.equals(this.name)) {
			return false;
		}

		DateInterval changeInterval = new DateInterval(data.start, data.end);

		for (String r : ShowRoom.roomAsList(data.rooms)) {
			ShowRoom sr = ShowRoom.valueOf(r);
			DateInterval newInterval = null;
			for (DateInterval d : openIntervals.get(sr)) {
				if (d.containInterval(changeInterval)) {
					newInterval = new DateInterval(DateInterval.shiftDate(changeInterval.getEnd(), 1), d.getEnd());
					d.setEnd(DateInterval.shiftDate(changeInterval.getStart(), -1));
					break;
				}
			}

			if (newInterval == null) {
				throw new DateIntervalException(changeInterval + " is not between display date");
			}
			openIntervals.get(sr).add(newInterval);
		}
		closeCount += 1;
		return true;
	}

	/**
	 * 換展件關閉展廳次數
	 */
	public int getCloseCount() {
		return this.closeCount;
	}


	@Override
	public String toString() {
		return String.format("Exhibition:\nName: %s\nDate: %s\nLocation: %s\nOpen Date:\n%s\nClose count: %s\n", name, getDisplayDate(), getRooms(), getOpenIntervals(), getCloseCount());
	}
}

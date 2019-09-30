package us.dontcareabout.googleSheet2;

import us.dontcareabout.googleSheet.RawData;
import us.dontcareabout.googleSheet2.Exceptions.RoomNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Exhibition2 {
	private String name;
	private Map<String, OpenIntervals> openIntervals = new HashMap<String, OpenIntervals>();

	public Exhibition2(RawData data) {
		this.name = data.getName();

		for (String room : data.getRooms().split(",")) {
			String r = room.trim().toUpperCase();
			openIntervals.put(r, new OpenIntervals());
			openIntervals.get(r).addOpenInterval(data.getStart(), data.getEnd());
		}
	}

	/**
	 * 新增換展件關閉展廳資訊
	 *
	 * @return 是否成功加入關閉展廳資訊
	 */
	public boolean addCloseInfo(RawData data) {
		if (!this.name.equals(data.getName())) return false;
		DateInterval closeInterval = new DateInterval(data.getStart(), data.getEnd());
		ArrayList<String> closeRoom = new ArrayList<String>();

		// 讀取關閉展廳
		for (String room : data.getRooms().split(",")) {
			String r = room.trim().toUpperCase();
			if (openIntervals.get(r) == null && r.length() == 5) {
				splitRoom(r.substring(0, r.length() - 1));
				closeRoom.add(r);
				continue;
			}

			if (openIntervals.get(r) == null && r.length() == 4) {
				for (String r2 : ShowRoom.roomAsList(r)) {
					if (!openIntervals.keySet().contains(r2)) throw new RoomNotFoundException(r);
					closeRoom.add(r2);
				}
				continue;
			}
			closeRoom.add(r);
		}

		// 加入關閉展廳資料
		for (String r : closeRoom) {
			openIntervals.get(r).removeCloseInterval(closeInterval);
		}
		return true;
	}

	private void splitRoom(String room) {
		if (openIntervals.get(room) == null) throw new RoomNotFoundException(room);

		OpenIntervals intervals = openIntervals.get(room);

		for (String r : ShowRoom.roomAsList(room)) {
			openIntervals.put(r, new OpenIntervals(intervals.getOpenIntervals()));
		}

		openIntervals.remove(room);
	}

	public String getName() {
		return name;
	}

	public Map<String, OpenIntervals> getOpenIntervals() {
		return openIntervals;
	}

	public Set<String> getRoom() {
		return openIntervals.keySet();
	}

	public DateInterval getDisplayDate() {
		OpenIntervals intervals = openIntervals.values().iterator().next();
		return intervals.getOpenRange();
	}

	@Override
	public String toString() {
		return String.format("Name: %s\nShow room: %s\nDisplay Date: %s\nOpen intervals:\n%s\n", name, getRoom(), getDisplayDate(), openIntervals);
	}
}

package us.dontcareabout.googleSheet2;

import us.dontcareabout.googleSheet.Exceptions.DateIntervalException;

import java.util.HashMap;
import java.util.Map;

public class Exhibition {
	private String name;
	private DateInterval displayDate;
	private String rooms;

	/**
	 * {@link Map} closeIntervals。key: {@link String} 為展廳名稱；value: {@link DateInterval} 為關閉時間
	 */
	private Map<String, DateInterval> closeIntervals = new HashMap<String, DateInterval>();

	public Exhibition(RawData data) {
		this.name = data.name;
		this.displayDate = new DateInterval(data.start, data.end);
		rooms = data.rooms;
	}

	public String getName() {
		return name;
	}

	public DateInterval getDisplayDate() {
		return displayDate;
	}

	public String getRooms() {
		return rooms;
	}

	/**
	 * @return {@link Map} closeIntervals。key: {@link String} 為展廳名稱；value: {@link DateInterval} 為關閉時間
	 */
	public Map<String, DateInterval> getCloseIntervals() {
		return closeIntervals;
	}

	/**
	 * 新增換展件關閉展廳資訊
	 *
	 * @return true 當成功取得關閉展廳資訊
	 */
	public boolean addCloseInfo(RawData data) {
		if (!data.name.equals(this.name)) {
			return false;
		}

		DateInterval changeInterval = new DateInterval(data.start, data.end);
		if (!displayDate.containInterval(changeInterval)) {
			throw new DateIntervalException(changeInterval + " is not between " + displayDate);
		}
		closeIntervals.put(data.rooms, new DateInterval(data.start, data.end));
		return true;
	}

	/**
	 * 換展件關閉展廳次數
	 */
	public int getCloseCount() {
		return closeIntervals.size();
	}


	@Override
	public String toString() {
		return String.format("Exhibition:\nName: %s\nDate: %s\nLocation: %s\nChangeInfo (%s):\n%s\n", name, displayDate, rooms, getCloseCount(), closeIntervals);
	}
}

package us.dontcareabout.googleSheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
	private final static String SHEET_ID = "1xRkXYlAioJe44AvhLpxuyZLfkvkmz9L0sPZo67feKH0";
	private final static String[] ALL_ROOMS = {"S101", "S102", "S201", "S202", "S203", "S204", "S301", "S302", "S303", "S304"};

	public static void main(String[] args) {
		ArrayList<RawData> dataTable = new Mapper<RawData>(SHEET_ID, 1).result;

		for (Exhibition e : getExhibitionInfo(dataTable)) {
			System.out.println(e);
		}

		for (ShowRoom sr : getRoomInfo(dataTable).values()) {
			System.out.println(sr);
		}
	}

	/**
	 * 取得所有展覽資訊
	 */
	public static List<Exhibition> getExhibitionInfo(List<RawData> dataTable) {
		List<Exhibition> exhibitions = new ArrayList<Exhibition>();

		for (RawData d : dataTable) {
			if (!d.close) {
				exhibitions.add(new Exhibition(d));
				continue;
			}

			boolean gotInfo = false;
			for (Exhibition e : exhibitions) {
				if (d.name.equals(e.name)) {
					e.addChangeInfo(d);
					gotInfo = true;
					break;
				}
			}
			if (!gotInfo) throw new RuntimeException(String.format("%s not exists", d.name));
		}
		return exhibitions;
	}

	public static Map<String, ShowRoom> getRoomInfo(List<RawData> dataTable) {
		Map<String, ShowRoom> showRoomMap = ShowRoom.createShowRooms(ALL_ROOMS);

		for (RawData d : dataTable) {
			for (String r : ShowRoom.roomAsList(d.rooms)) {
				if (d.close) {
					showRoomMap.get(r).addClose(d.name, d.start, d.end);
					break;
				}
				showRoomMap.get(r).addOpen(d.name, d.start, d.end);
			}
		}
		return showRoomMap;
	}
}

package us.dontcareabout.googleSheet;

import java.util.ArrayList;
import java.util.List;

public class Main {
	private final static String SHEET_ID = "1xRkXYlAioJe44AvhLpxuyZLfkvkmz9L0sPZo67feKH0";

	public static void main(String[] args) {
		ArrayList<RawData> dataTable = new Mapper<RawData>(SHEET_ID, 1).result;

		for (Exhibition e : getExhibitionInfo(dataTable)) {
			System.out.println(e);
		}

		getRoomInfo(dataTable);
		for (ShowRoom sr : ShowRoom.values()) {
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
				if (d.name.equals(e.getName())) {
					e.addChangeInfo(d);
					gotInfo = true;
					break;
				}
			}
			if (!gotInfo) throw new RuntimeException(String.format("%s not exists", d.name));
		}
		return exhibitions;
	}

	public static void getRoomInfo(List<RawData> dataTable) {

		for (RawData d : dataTable) {
			for (String r : ShowRoom.roomAsList(d.rooms)) {
				if (d.close) {
					ShowRoom.valueOf(r).addClose(d.name, d.start, d.end);
					break;
				}
				ShowRoom.valueOf(r).addOpen(d.name, d.start, d.end);
			}
		}
	}
}

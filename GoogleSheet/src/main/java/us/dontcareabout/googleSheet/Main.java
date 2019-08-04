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
}

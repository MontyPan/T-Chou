package us.dontcareabout.googleSheet2;

import java.util.ArrayList;
import java.util.List;

public class Main {
	private final static String SHEET_ID = "1xRkXYlAioJe44AvhLpxuyZLfkvkmz9L0sPZo67feKH0";

	public static void main(String[] args) {
		ArrayList<RawData> dataTable = new Mapper<RawData>(SHEET_ID, 1).result;

		for (Exhibition2 e : getExhibitionInfo(dataTable)) {
			System.out.println(e);
		}
	}

	/**
	 * 取得所有展覽資訊
	 */
	public static List<Exhibition2> getExhibitionInfo(List<RawData> dataTable) {
		List<Exhibition2> exhibitions = new ArrayList<Exhibition2>();

		for (RawData d : dataTable) {
			if (!d.close) {
				exhibitions.add(new Exhibition2(d));
			}
		}
		return exhibitions;
	}
}

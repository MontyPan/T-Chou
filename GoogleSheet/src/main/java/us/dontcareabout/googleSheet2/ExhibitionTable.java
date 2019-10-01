package us.dontcareabout.googleSheet2;

import us.dontcareabout.googleSheet.Mapper;
import us.dontcareabout.googleSheet.RawData;
import us.dontcareabout.googleSheet2.Exceptions.ExhibitionNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExhibitionTable {
	private ArrayList<Exhibition2> exhibitions = new ArrayList<>();

	/**
	 * 從 Google Sheet 讀取資料
	 */
	public void loadFromGoogleSheet(String sheetId) {
		ArrayList<RawData> dataTable = new Mapper(sheetId, 1).result;
		loadExhibitionInfo(dataTable);
		loadCloseInfo(dataTable);
	}

	/**
	 * 讀取展覽資料
	 */
	private void loadExhibitionInfo(ArrayList<RawData> dataTable) {
		for (RawData d : dataTable) {
			if (!d.isClose()) {
				exhibitions.add(new Exhibition2(d));
			}
		}
	}

	/**
	 * 讀取換展資料
	 */
	private void loadCloseInfo(ArrayList<RawData> dataTable) {
		for (RawData d : dataTable) {
			if (!d.isClose()) continue;

			boolean getInfo = false;
			for (Exhibition2 e : exhibitions) {
				if (d.getName().equals(e.getName())) {
					getInfo = e.addCloseInfo(d);
				}
			}

			if (!getInfo) throw new ExhibitionNotFoundException(d.getName());
		}
	}

	public List<Exhibition2> asList() {
		return Collections.unmodifiableList(exhibitions);
	}
}

package us.dontcareabout.googleSheet2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import us.dontcareabout.googleSheet.GoogleSheet;
import us.dontcareabout.googleSheet.RawData;

import java.util.ArrayList;

public class Mapper<T> {
	public final ArrayList<T> result;

	public Mapper(String sheetId, int tabIndex) {
		result = new Gson().fromJson(
				GoogleSheet.entryJson(sheetId, tabIndex),
				new TypeToken<ArrayList<RawData>>() {
				}.getType()
		);

	}
}

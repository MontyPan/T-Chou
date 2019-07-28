package us.dontcareabout.googleSheet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

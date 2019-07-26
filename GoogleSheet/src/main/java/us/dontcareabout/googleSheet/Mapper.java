package us.dontcareabout.googleSheet;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Mapper<T> {
	public final ArrayList<T> result;

	public Mapper(String sheetId, int tabIndex) {
		result = new Gson().fromJson(
				GoogleSheet.entryJson(sheetId, tabIndex),
				new TypeToken<ArrayList<Foo>>(){}.getType()
			);

	}
}

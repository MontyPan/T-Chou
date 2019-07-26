package us.dontcareabout.googleSheet;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;

public class GoogleSheet {
	private static final String URL_HEAD = "https://spreadsheets.google.com/feeds/list/";
	private static final String URL_TAIL = "/public/values?alt=json";

	static {
		//HTTPS 連線需要，統一放在這裡
		System.setProperty("https.protocols", "TLSv1.2");
	}

	public static String url(String sheetId, int tabIndex) {
		return URL_HEAD + sheetId + "/" + tabIndex + URL_TAIL;
	}

	public static String allJson(String sheetId, int tabIndex) {
		Connection conn = Jsoup.connect(url(sheetId, 1))
			.validateTLSCertificates(false).ignoreContentType(true);
		conn.request().method(Method.GET);

		try {
			conn.execute();
			return conn.response().body();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String entryJson(String sheetId, int tabIndex) {
		String json = GoogleSheet.allJson(sheetId, tabIndex);
		String entry = "\"entry\":";
		json = json.substring(json.indexOf(entry) + entry.length(), json.lastIndexOf("]") + 1);

		int startIndex = 0;
		int[] range;

		ArrayList<String> entryList = new ArrayList<String>();
		while((range = range(json, PAIR[1], startIndex)) != null) {
			entryList.add(clean(section(json, range)));
			startIndex = range[1] + 1;
		}

		return combine(entryList, PAIR[2]);
	}


	/**
	 * 純粹是紀念作品。
	 * 搞不懂為啥 {@link Mapper} 那樣就行、這邊這樣搞就不行...... Orz
	 */
	public static <T> ArrayList<T> entry(String sheetId, int tabIndex) {
		return new Gson().fromJson(
			GoogleSheet.entryJson(sheetId, 1),
			new TypeToken<ArrayList<T>>(){}.getType()
		);
	}

	/////////////////////////////////////////////////////////

	private static String[][] PAIR = {{"\"", "\""}, {"{", "}"}, {"[", "]"}};

	/**
	 * 取得指定 pair（成對邏輯上）的 index 區間
	 */
	private static int[] range(String string, String[] pair, int startIndex) {
		int start = string.indexOf(pair[0], startIndex);

		if (start == -1) { return null; }


		int end = string.indexOf(pair[1], start + 1);

		if (end == -1) { return null; }

		int noiseStart = string.indexOf(pair[0], start + 1);
		while (noiseStart != -1 && noiseStart < end) {
			end = string.indexOf(pair[1], end + 1);
			if (end == -1) { return null; }
			noiseStart = string.indexOf(pair[0], noiseStart + 1);
		}

		return new int[]{start, end};
	}

	/**
	 * 指定區間的字串
	 *
	 * @see #range(String, String[], int)
	 */
	private static String section(String string, int[] index) {
		return index == null ? null : string.substring(index[0], index[1] + 1);
	}

	/**
	 * 將一個 list 中每個 item 前後夾上 pair，然後用逗號分隔
	 */
	private static String combine(ArrayList<String> list, String[] pair) {
		StringBuffer result = new StringBuffer(pair[0]);

		for (int i = 0; i < list.size() - 1; i++) {
			result.append(list.get(i));
			result.append(",");
		}

		result.append(list.get(list.size() - 1));
		result.append(pair[1]);
		return result.toString();
	}

	private static String gsx = "gsx$";
	/**
	 * 移除所有非 gsx 的 field，
	 * 而本來要用 gsx$foo.$t 才能取到的值，改成 foo 就能取到
	 */
	private static String clean(String entry) {
		ArrayList<String> result = new ArrayList<String>();

		int start = 0;
		int[] range;
		String name;

		while((range = range(entry, PAIR[0], start)) != null) {
			name = section(entry, range);
			name = name.substring(1, name.length() - 1);

			if (name.startsWith(gsx)) {
				result.add(
					"\"" + name.substring(gsx.length(), name.length()) + "\":" +
					section(entry, range(entry, PAIR[0], range[1] + 8))
				);
			}

			start = range[1] + 1;
		}

		return combine(result, PAIR[1]);
	}

}

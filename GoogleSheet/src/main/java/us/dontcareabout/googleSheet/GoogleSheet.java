package us.dontcareabout.googleSheet;

import java.io.IOException;

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

	public static String json(String sheetId) {
		return json(sheetId, 1);
	}

	public static String json(String sheetId, int tabIndex) {
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
}

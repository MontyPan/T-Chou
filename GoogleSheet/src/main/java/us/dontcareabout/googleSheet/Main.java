package us.dontcareabout.googleSheet;

public class Main {
	private final static String SHEET_ID = "1xRkXYlAioJe44AvhLpxuyZLfkvkmz9L0sPZo67feKH0";

	public static void main(String[] args) throws Exception {
		for (RawData f : new Mapper<RawData>(SHEET_ID, 1).result) {
			System.out.println(f);
		}
	}
}
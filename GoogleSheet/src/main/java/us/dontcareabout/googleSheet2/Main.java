package us.dontcareabout.googleSheet2;

public class Main {
	private final static String SHEET_ID = "1xRkXYlAioJe44AvhLpxuyZLfkvkmz9L0sPZo67feKH0";

	public static void main(String[] args) {
		ExhibitionTable exhibitionTable = new ExhibitionTable();
		exhibitionTable.loadFromGoogleSheet(SHEET_ID);

		for (Exhibition2 e : exhibitionTable.asList()) {
			System.out.println(e);
		}
	}
}

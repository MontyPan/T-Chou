package us.dontcareabout.chou.ticTacToe;

public class ShowBoard {
	public void printBoard(Board board) {
		int len = Board.N;

		// create inputBoard
		char[][] inputBoard = new char[len][len];
		char[][] playBoard = new char[len][len];

		for (int idx = 0; idx < len; idx++) {
			for (int idx2 = 0; idx2 < len; idx2++) {
				int fillNumber = idx * len + idx2 + 1;
				inputBoard[idx][idx2] = (char) (fillNumber + '0');

				Boolean value = board.getStatus(idx, idx2);
				playBoard[idx][idx2] = String.valueOf(value).charAt(0);
			}
		}

		// seperator between row
		char[] rowSep = new char[len];

		for (int idx = 0; idx < len; idx++) {
			rowSep[idx] = '-';
		}

		System.out.println("TicTacToe:");

		// print out boards
		for (int idx = 0; idx < len; idx++) {
			String board1 = array2String(playBoard[idx], "|");
			String board2 = array2String(inputBoard[idx], "|");
			String spaces = "        ";
			System.out.println(board1 + spaces + board2);

			if (idx != len - 1) {
				String grid = array2String(rowSep, "+");
				System.out.println(grid + spaces + grid);
			}
		}
	}

	/**
	 * Convert a char array to a string
	 */
	private String array2String(char[] array, String sep) {
		int len = array.length;
		String msg = "";

		for (int idx = 0; idx < len; idx++) {
			msg += array[idx];

			if (idx != len - 1) {
				msg += sep;
			}
		}
		return msg;
	}
}

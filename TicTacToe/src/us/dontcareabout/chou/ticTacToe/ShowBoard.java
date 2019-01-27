package us.dontcareabout.chou.ticTacToe;

public class ShowBoard {
	private int N = Board.N;
	private char[][] inputBoard = new char[N][N];
	private char[] rowSep = new char[N];

	public ShowBoard() {
		for (int idx = 0; idx < N; idx++) {
			for (int idx2 = 0; idx2 < N; idx2++) {
				int fillNumber = idx * N + idx2 + 1;
				inputBoard[idx][idx2] = (char) (fillNumber + '0');
			}
			rowSep[idx] = '-';
		}
	}

	public void printBoard(Board board) {
		char[][] playBoard = new char[N][N];

		for (int idx = 0; idx < N; idx++) {
			for (int idx2 = 0; idx2 < N; idx2++) {
				Boolean value = board.getStatus(idx, idx2);
				playBoard[idx][idx2] = getMark(value);
			}
		}

		System.out.println("TicTacToe:");

		// print out boards
		for (int idx = 0; idx < N; idx++) {
			String board1 = array2String(playBoard[idx], "|");
			String board2 = array2String(inputBoard[idx], "|");
			String spaces = "        ";
			System.out.println(board1 + spaces + board2);

			if (idx != N - 1) {
				String grid = array2String(rowSep, "+");
				System.out.println(grid + spaces + grid);
			}
		}
	}

	/**
	 * turn Boolean value to char value for player
	 */
	private char getMark(Boolean player) {
		if (player != null && player) {
			return 'O';
		}

		if (player != null && !player) {
			return 'X';
		}

		return ' ';
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

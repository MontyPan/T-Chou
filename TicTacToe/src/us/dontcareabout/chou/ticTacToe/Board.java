package us.dontcareabout.chou.ticTacToe;

public class Board {
	public static final int N = 3;
	char[][] playBoard = new char[N][N];
	boolean player = true;

	public Board() {
		System.out.println("Board");
		initBoard();
	}

	public void initBoard() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				playBoard[i][j] = ' ';
			}
		}
	}

	/**
	 * @param i the last input of row
	 * @param j the last input of column
	 * @return true if the last input makes the player win.
	 */
	public boolean checkWin(int i, int j) {
		char marker = playBoard[i][j];
		int[] count2Three = {0, 0, 0, 0};

		for (int idx = 0; idx < N; idx++) {
			if (playBoard[i][idx] == marker) {
				count2Three[0] += 1;
			}
			if (playBoard[idx][j] == marker) {
				count2Three[1] += 1;
			}
			if (playBoard[idx][idx] == marker) {
				count2Three[2] += 1;
			}
			if (playBoard[idx][N - 1 - idx] == marker) {
				count2Three[3] += 1;
			}
		}

		for (int idx = 0; idx < count2Three.length; idx++) {
			if (count2Three[idx] == N) {
				return true;
			}
		}
		return false;
	}

	/**
	 * place stones on position i, j.
	 *
	 * @param i number of row
	 * @param j number of column
	 * @return true if the position i, j is empty where stones can be placed.
	 */
	public boolean placeStone(int i, int j) {
		char marker = ' ';

		if (player) {
			marker = 'O';
		} else {
			marker = 'X';
		}

		if (playBoard[i][j] == ' ') {
			playBoard[i][j] = marker;
			return true;
		} else {
			System.out.println("The position " + i + ", " + j + " is not available!");
			return false;
		}
	}

	/**
	 * Print TicTacToe board
	 */
	public void printBoard() {

		// create inputBoard
		char[][] inputBoard = new char[N][N];

		for (int idx1 = 0; idx1 < N; idx1++) {
			for (int idx2 = 0; idx2 < N; idx2++) {
				int number = idx1 * N + idx2 + 1;
				inputBoard[idx1][idx2] = (char) (number + '0');
			}
		}

		// row grid
		char[] rowGrid = new char[N];
		for (int idx = 0; idx < N; idx++) {
			rowGrid[idx] = '-';
		}

		// print out
		for (int idx = 0; idx < N; idx++) {
			String board1 = charArray2String(playBoard[idx], "|");
			String board2 = charArray2String(inputBoard[idx], "|");
			String spaces = "       ";
			System.out.println(board1 + spaces + board2);

			if (idx != N - 1) {
				String grid = charArray2String(rowGrid, "+");
				System.out.println(grid + spaces + grid);
			}
		}
	}

	public String charArray2String(char[] charArray, String sep) {
		int len = charArray.length;
		String str = "";

		for (int idx = 0; idx < len; idx++) {
			str += charArray[idx];

			if (idx != len - 1) {
				str += sep;
			}
		}

		return str;
	}
}

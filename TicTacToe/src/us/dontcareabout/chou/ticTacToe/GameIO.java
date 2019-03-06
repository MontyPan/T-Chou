package us.dontcareabout.chou.ticTacToe;

import java.util.Scanner;

public class GameIO {
	private static final int N = Board.N;

	private final String grid;

	private String spaces = "        ";
	private String[] inputBoard = new String[N];

	public GameIO() {
		char[] inputRow = new char[N];
		char[] rowSep = new char[N];

		for (int idx = 0; idx < N; idx++) {
			for (int idx2 = 0; idx2 < N; idx2++) {
				int fillNumber = idx * N + idx2 + 1;
				inputRow[idx2] = (char) (fillNumber + '0');
			}
			inputBoard[idx] = array2String(inputRow, "|");
			rowSep[idx] = '-';
		}

		grid = array2String(rowSep, "+");
	}

	public void printBoard(Board board) {
		char[][] playBoard = new char[N][N];

		for (int idx = 0; idx < N; idx++) {
			for (int idx2 = 0; idx2 < N; idx2++) {
				playBoard[idx][idx2] = getMark(board.getStatus(idx, idx2));
			}
		}

		System.out.println("\nTicTacToe:");

		// print out boards
		for (int idx = 0; idx < N; idx++) {
			String board1 = array2String(playBoard[idx], "|");
			System.out.println(board1 + spaces + inputBoard[idx]);

			if (idx != N - 1) {
				System.out.println(grid + spaces + grid);
			}
		}
	}

	/**
	 * turn Boolean value to char value for player
	 */
	private char getMark(Boolean player) {
		if (player == null) {
			return ' ';
		}

		return player ? 'O' : 'X';
	}

	/**
	 * turn Boolean value to player name
	 */
	private String getName(boolean player) {
		return player ? "Player 1" : "Player 2";
	}

	/**
	 * Convert a char array to a string
	 */
	private String array2String(char[] array, String sep) {
		StringBuilder result = new StringBuilder("" + array[0]);

		for (int idx = 1; idx < array.length; idx++) {
			result.append(sep + array[idx]);
		}

		return result.toString();
	}

	/**
	 * @return a number input by player
	 */
	public int getPosition(boolean player) {
		System.out.println(getName(player) + " input:");

		while (true) {
			try {
				return getInputNumber();
			} catch (Exception e) {
				System.out.print("Please input a number between 1 and 9:");
			}
		}
	}

	@SuppressWarnings("resource")
	private int getInputNumber() throws Exception {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();

		if (number < 1 || number > N * N) {
			throw new Exception("Number must be between 1 and 9");
		}
		return number;
	}

	public void showResult(Boolean winner) {
		if (winner == null) {
			System.out.println("Tie!");
		} else {
			System.out.println(getName(winner) + " wins!");
		}
	}

	public void inputError(int pos) {
		System.out.println("Position " + pos + " is not available");
	}
}


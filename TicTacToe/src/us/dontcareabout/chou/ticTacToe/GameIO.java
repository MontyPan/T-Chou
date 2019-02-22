package us.dontcareabout.chou.ticTacToe;

import java.util.Scanner;

public class GameIO {
	private int N = Board.N;
	private char[][] inputBoard = new char[N][N];
	private char[] rowSep = new char[N];


	public GameIO() {
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

		System.out.println("\nTicTacToe:");

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
		if (player == null) {
			return ' ';
		}

		if (player) {
			return 'O';
		}

		return 'X';
	}

	/**
	 * turn Boolean value to player name
	 */
	public String getName(boolean player) {
		if (player) {
			return "Player 1";
		}

		return "Player 2";
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

	/**
	 * @return a coordinate of a number input by player
	 */
	public int[] getPosition(String player) {
		int[] pos = new int[2];
		int input;

		System.out.println(player + " input:");

		while (true) {
			try {
				input = getInputNumber();
				break;
			} catch (Exception e) {
				System.out.print("Please input a number between 1 and 9:");
			}
		}

		int row = (input - 1) / Board.N;
		int column = (input - 1) % Board.N;

		pos[0] = row;
		pos[1] = column;

		return pos;
	}

	@SuppressWarnings("resource")
	private int getInputNumber() throws Exception {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();

		if (number < 1 || number > 9) {
			throw new Exception("Number must be between 1 and 9");
		}
		return number;
	}

	public void showResult(Boolean winner) {
		if (winner == null) {
			System.out.println("Tie!");
			return;
		}
		System.out.println(getName(winner) + " wins!");
	}

	public void inputError(int[] pos) {
		System.out.println("Position " + pos[0] + ", " + pos[1] + " is not available");
	}
}


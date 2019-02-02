package us.dontcareabout.chou.ticTacToe;

import java.util.Scanner;

public class Game {
	private int N = Board.N;
	private char[][] inputBoard = new char[N][N];
	private char[] rowSep = new char[N];
	private Board board;

	public Game(Board playBoard) {
		board = playBoard;

		for (int idx = 0; idx < N; idx++) {
			for (int idx2 = 0; idx2 < N; idx2++) {
				int fillNumber = idx * N + idx2 + 1;
				inputBoard[idx][idx2] = (char) (fillNumber + '0');
			}
			rowSep[idx] = '-';
		}
		printBoard();
	}

	/**
	 * @return true if the game is end
	 */
	public boolean playGame() {
		if (!board.hasEmpty()) {
			System.out.println("Tie!");
			return true;
		}

		String player = getName(board.getCurrentPlayer());
		System.out.println(player + " input:");
		int[] pos = getPosition();

		if (board.placeStone(pos[0], pos[1])) {
			System.out.println();
			printBoard();

			if (board.checkWin(pos[0], pos[1])) {
				String winner = getName(board.getCurrentPlayer());
				System.out.println(winner + " wins!");
				return true;
			}
			board.switchPlayer();
		} else {
			System.out.println("Position " + pos[0] + ", " + pos[1] + " is not available");
		}
		return false;
	}

	public void printBoard() {
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
	 * turn Boolean value to player name
	 */
	private String getName(boolean player) {
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
	 * @return a coordinate of a number on inputBoard
	 */
	private int[] getPosition() {
		int[] pos = new int[2];
		int input;

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
}

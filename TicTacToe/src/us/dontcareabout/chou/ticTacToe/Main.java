package us.dontcareabout.chou.ticTacToe;

import java.util.Scanner;

public class Main {
	public static final int N = 3;

	public static void main(String[] args) {
		Board board = new Board();
		printBoard(board.playBoard);

		int maxTurn = board.N * board.N;
		int turns = 0;
		String playerName;

		while (turns < maxTurn) {
			if (board.player) {
				playerName = "player 1";
			} else {
				playerName = "player 2";
			}

			System.out.println(playerName + " input:");
			int[] pos = getPosition();

			if (board.placeStone(pos[0], pos[1])) {
				System.out.println();
				printBoard(board.playBoard);

				if (board.checkWin(pos[0], pos[1])) {
					System.out.println(playerName + " wins!");
					break;
				}
				board.player = !board.player;
				turns += 1;
			}
		}

		if (turns == maxTurn) {
			System.out.println("Tie!");
		}
	}


	// Get position i, j from numpad
	private static int[] getPosition() {
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

		int row = (input - 1) / N;
		int column = (input - 1) % N;

		pos[0] = row;
		pos[1] = column;

		return pos;
	}

	// print TicTacToe board
	private static void printBoard(char[][] playBoard) {
		int len = playBoard.length;

		// create inputBoard
		char[][] inputBoard = new char[len][len];

		for (int idx = 0; idx < len; idx++) {
			for (int idx2 = 0; idx2 < len; idx2++) {
				int fillNumber = idx * len + idx2 + 1;
				inputBoard[idx][idx2] = (char) (fillNumber + '0');
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
			String board1 = charArray2String(playBoard[idx], "|");
			String board2 = charArray2String(inputBoard[idx], "|");
			String spaces = "        ";
			System.out.println(board1 + spaces + board2);

			if (idx != len - 1) {
				String grid = charArray2String(rowSep, "+");
				System.out.println(grid + spaces + grid);
			}
		}
	}

	/**
	 * Convert a char array to a string
	 */
	private static String charArray2String(char[] charArray, String sep) {
		int len = charArray.length;
		String msg = "";

		for (int idx = 0; idx < len; idx++) {
			msg += charArray[idx];

			if (idx != len - 1) {
				msg += sep;
			}
		}
		return msg;
	}


	@SuppressWarnings("resource")
	private static int getInputNumber() throws Exception {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();

		if (number < 1 || number > 9) {
			throw new Exception("Number must be between 1 and 9");
		}
		return number;
	}
}

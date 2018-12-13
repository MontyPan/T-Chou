package us.dontcareabout.chou.ticTacToe;

import java.util.Scanner;

public class Main {
	public static final int N = 3;

	public static void main(String[] args) {
		char[][] playBoard = new char[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				playBoard[i][j] = ' ';
			}
		}

		boolean player1 = true;
		String playerName = "";

		printBoard(playBoard);
		int turns = 0;

		while (turns < N * N) {
			if (player1) {
				playerName = "Player 1";
			} else {
				playerName = "Player 2";
			}

			System.out.println(playerName + " input:");
			int[] pos = getPosition();

			if (placeStone(pos[0], pos[1], player1, playBoard)) {
				System.out.println();
				printBoard(playBoard);

				if (checkWin(pos[0], pos[1], playBoard)) {
					System.out.println(playerName + " wins!");
					break;
				}

				player1 = !player1;        // switch player
				turns += 1;
			}
		}

		if (turns == N * N) {
			System.out.println("Tie!");
		}

	}

	/**
	 * @param i the last input of row
	 * @param j the last input of column
	 * @return true if the last input makes the player win.
	 */
	private static boolean checkWin(int i, int j, char[][] playBoard) {
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

	// Get position i, j from numpad
	private static int[] getPosition() {
		int[] pos = new int[2];

		int input = getInputNumber();

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

	/**
	 * place stones on position i, j.
	 *
	 * @param i number of row
	 * @param j number of column
	 * @return true if the position i, j is empty where stones can be placed.
	 */
	private static boolean placeStone(int i, int j, boolean player, char[][] playBoard) {
		char marker = '-';

		if (player) {
			marker = 'O';
		} else {
			marker = 'X';
		}

		if (playBoard[i][j] == ' ') {
			playBoard[i][j] = marker;
			return true;
		} else {
			System.out.println("The position " + i + ", " + j + " is not available.");
			return false;
		}
	}

	@SuppressWarnings("resource")
	private static int getInputNumber() {
		Scanner scanner = new Scanner(System.in);
		boolean valid = false;
		int number = -1;

		while (!valid) {
			number = scanner.nextInt();

			if (number >= 1 && number <= 9) {
				valid = true;
			} else {
				System.out.print("Please input a number between 1 and 9:");
			}
		}
		return number;
	}

}

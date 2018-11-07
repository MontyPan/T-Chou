package us.dontcareabout.chou.ticTacToe;

import java.util.Scanner;

public class Main {
	public static final int N = 3;

	public static void main(String[] args) {
		char[][] playBoard;
		playBoard = new char[N][N];

		for (int i=0; i<N; i++) {
			for (int j=0;j<N;j++) {
				playBoard[i][j] ='-';
			}
		}

		boolean player1 = true;
		String playerName = "";

		printBoard(playBoard);
		int turns = 0;

		while (turns < 9) {
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

		if (turns == 9) {
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
		boolean checkRow = true, checkColumn = true;

		for (int idx = 0; idx < 3; idx++) {
			if (playBoard[i][idx] != marker) {
				checkRow = false;
				break;
			}
		}
		for (int idx = 0; idx < 3; idx++) {
			if (playBoard[idx][j] != marker) {
				checkColumn = false;
				break;
			}
		}

		if (checkRow || checkColumn) {
			return true;
		}

		if (playBoard[1][1] == marker && ((i + j) % 2 == 0)) {
			if (playBoard[0][0] == marker && playBoard[2][2] == marker) {
				return true;
			}
			if (playBoard[0][2] == marker && playBoard[2][0] == marker) {
				return true;
			}
		}

		return false;
	}

	// Get position i, j from numpad
	private static int[] getPosition() {
		int num = getInputNumber();

		int[][] pos = {{2, 0}, {2, 1}, {2, 2}, {1, 0}, {1, 1}, {1, 2}, {0, 0}, {0, 1}, {0, 2}};
		return pos[num - 1];
	}

	// print TicTacToe board
	private static void printBoard(char[][] playBoard) {
		int len = playBoard.length;
		System.out.println("TicTacToe:");

		for (int idx = 0; idx < len; idx++) {
			System.out.println(playBoard[idx]);
		}
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

		if (playBoard[i][j] == '-') {
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
		return scanner.nextInt();
	}

}

package us.dontcareabout.chou.ticTacToe;

import java.util.Arrays;
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
		int[][] inputBoard = new int[len][len];
		char[] columnSep = new char[len];
		char[] rowSep = new char[2 * len];

		// create inputBoard
		for (int idx = 0; idx < len; idx++) {
			for (int idx2 = 0; idx2 < len; idx2++) {
				inputBoard[idx][idx2] = idx * len + idx2 + 1;
			}
		}

		// separator between coloumn
		for (int idx = 0; idx < len; idx++) {
			if (idx == len - 1) {
				columnSep[idx] = ' ';
			} else {
				columnSep[idx] = '|';
			}
		}

		// seperator between row
		for (int idx = 0; idx < 2 * len; idx++) {
			if (idx == 2 * len - 1) {
				rowSep[idx] = ' ';
			} else {
				if (idx % 2 == 0) {
					rowSep[idx] = '-';
				} else {
					rowSep[idx] = '+';
				}
			}
		}

		System.out.println("TicTacToe:");

		for (int idx = 0; idx < len; idx++) {
			// print playBoard row
			for (int idx2 = 0; idx2 < len; idx2++) {
				System.out.printf("%c", playBoard[idx][idx2]);
				System.out.printf("%c", columnSep[idx2]);
			}

			System.out.printf("          ");
			// print inputBoard row
			for (int idx2 = 0; idx2 < len; idx2++) {
				System.out.printf("%d", inputBoard[idx][idx2]);
				System.out.printf("%c", columnSep[idx2]);
			}

			System.out.printf("\n");
			if (idx != len - 1) {
				System.out.printf(String.valueOf(rowSep));
				System.out.printf("          ");
				System.out.printf(String.valueOf(rowSep));
				System.out.printf("\n");
			}
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
		return scanner.nextInt();
	}

}

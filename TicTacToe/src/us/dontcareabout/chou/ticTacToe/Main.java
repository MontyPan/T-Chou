package us.dontcareabout.chou.ticTacToe;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		char[][] playBoard = {{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
		boolean player1 = true;

		printBoard(playBoard);

		while (true) {
			if(player1) {
				System.out.println("Player 1 input:");
			} else {
				System.out.println("Player 2 input:");
			}

			int[] pos = getPosition();

			if (placeStone(pos[0], pos[1], player1, playBoard)) {
				player1 = switchPlayer(player1);
				System.out.println();
				printBoard(playBoard);
			}
		}
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

	// Switch players between 1 and 2
	private static boolean switchPlayer(boolean player) {
		if (player) {
			return false;
		} else {
			return true;
		}
	}

	// place stones on position i, j.
	// Return true if the position i, j is empty where stones can be placed.
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

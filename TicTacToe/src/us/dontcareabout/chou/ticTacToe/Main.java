package us.dontcareabout.chou.ticTacToe;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		char[][] playBoard = {{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
		int player = 1;

		printBoard(playBoard);

		while (true) {
			System.out.println("Player " + player + " input:");
			int[] pos = getPosition();

			if (placeStone(pos[0], pos[1], player, playBoard)) {
				player = switchPlayer(player);
				System.out.println();
				printBoard(playBoard);
			}
		}
	}

	// Get position i, j from numpad
	private static int[] getPosition() {
		int num = getInputNumber();
		int[] pos = {10, 10};

		switch (num) {
			case (1): {
				pos[0] = 2;
				pos[1] = 0;
				break;
			}
			case (2): {
				pos[0] = 2;
				pos[1] = 1;
				break;
			}
			case (3): {
				pos[0] = 2;
				pos[1] = 2;
				break;
			}
			case (4): {
				pos[0] = 1;
				pos[1] = 0;
				break;
			}
			case (5): {
				pos[0] = 1;
				pos[1] = 1;
				break;
			}
			case (6): {
				pos[0] = 1;
				pos[1] = 2;
				break;
			}
			case (7): {
				pos[0] = 0;
				pos[1] = 0;
				break;
			}
			case (8): {
				pos[0] = 0;
				pos[1] = 1;
				break;
			}
			case (9): {
				pos[0] = 0;
				pos[1] = 2;
				break;
			}
		}
		return pos;
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
	private static int switchPlayer(int player) {
		if (player == 1) {
			player = 2;
		} else if (player == 2) {
			player = 1;
		}
		return player;
	}

	// place stones on position i, j.
	// Return true if the position i, j is empty where stones can be placed.
	private static boolean placeStone(int i, int j, int player, char[][] playBoard) {
		char marker = '-';

		if (player == 1) {
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

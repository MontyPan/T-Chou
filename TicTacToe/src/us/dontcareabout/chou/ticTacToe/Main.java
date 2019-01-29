package us.dontcareabout.chou.ticTacToe;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Board board = new Board();
		Game game = new Game();
		game.printBoard(board);

		int maxTurn = Board.N * Board.N;
		int turns = 0;
		String playerName;

		while (turns < maxTurn) {
			if (board.getCurrentPlayer()) {
				playerName = "player 1";
			} else {
				playerName = "player 2";
			}

			System.out.println(playerName + " input:");
			int[] pos = getPosition();

			if (board.placeStone(pos[0], pos[1])) {
				System.out.println();
				game.printBoard(board);

				if (board.checkWin(pos[0], pos[1])) {
					System.out.println(playerName + " wins!");
					break;
				}
				board.switchPlayer();
				turns += 1;
			} else {
				System.out.println("Position " + pos[0] + ", " + pos[1] + " is not available");
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

		int row = (input - 1) / Board.N;
		int column = (input - 1) % Board.N;

		pos[0] = row;
		pos[1] = column;

		return pos;
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

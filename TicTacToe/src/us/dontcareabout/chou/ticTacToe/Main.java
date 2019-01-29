package us.dontcareabout.chou.ticTacToe;

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
			int[] pos = game.getPosition();

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
}

package us.dontcareabout.chou.ticTacToe;

public class Main {

	public static void main(String[] args) {
		Board board = new Board();
		Game game = new Game();
		game.printBoard(board);

		String playerName;

		while (true) {
			if (!board.hasEmpty()) {
				System.out.println("Tie!");
				break;
			}

			playerName = game.getName(board.getCurrentPlayer());

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
			} else {
				System.out.println("Position " + pos[0] + ", " + pos[1] + " is not available");
			}
		}
	}
}

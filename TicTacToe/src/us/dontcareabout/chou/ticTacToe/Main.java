package us.dontcareabout.chou.ticTacToe;

public class Main {

	public static void main(String[] args) {
		Board board = new Board();
		Game game = new Game();
		game.printBoard(board);

		boolean isEnd = false;

		while (!isEnd) {
			if (!board.hasEmpty()) {
				System.out.println("Tie!");
				break;
			}
			isEnd = game.playGame(board);
		}

		if (isEnd) {
			String winner = game.getName(board.getCurrentPlayer());
			System.out.println(winner + " wins!");
		}
	}
}

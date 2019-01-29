package us.dontcareabout.chou.ticTacToe;

public class Main {

	public static void main(String[] args) {
		Board board = new Board();
		Game game = new Game(board);

		boolean isEnd = false;

		while (!isEnd) {
			if (!board.hasEmpty()) {
				System.out.println("Tie!");
				break;
			}
			isEnd = game.playGame();
		}

		if (isEnd) {
			String winner = game.getName(board.getCurrentPlayer());
			System.out.println(winner + " wins!");
		}
	}
}

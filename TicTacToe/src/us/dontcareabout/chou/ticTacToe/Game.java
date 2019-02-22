package us.dontcareabout.chou.ticTacToe;

public class Game {
	private Board board = new Board();
	private GameIO io = new GameIO();
	private Boolean winner;

	public Game() {
		io.printBoard(board);
	}

	/**
	 * @return true if the game is end
	 */
	public boolean playGame() {
		if (!board.hasEmpty()) {
			return true;
		}

		String player = io.getName(board.getCurrentPlayer());
		int[] pos = io.getPosition(player);

		if (board.placeStone(pos[0], pos[1])) {
			System.out.println();
			io.printBoard(board);

			if (board.checkWin(pos[0], pos[1])) {
				winner = board.getCurrentPlayer();
				return true;
			}
			board.switchPlayer();
		} else {
			System.out.println("Position " + pos[0] + ", " + pos[1] + " is not available");
		}
		return false;
	}

	public void showResult() {
		if (winner == null) {
			System.out.println("Tie!");
			return;
		}
		System.out.println(io.getName(winner) + " wins!");
	}
}
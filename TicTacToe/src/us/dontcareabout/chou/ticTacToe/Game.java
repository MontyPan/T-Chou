package us.dontcareabout.chou.ticTacToe;

public class Game {
	private Board board = new Board();
	private GameIO io = new GameIO();
	private Boolean winner;

	public Game() {
		io.printBoard(board);
	}

	public void playGame() {
		while (round()) {}
	}

	/**
	 * @return true if the game is still going
	 */
	private boolean round() {
		if (!board.hasEmpty()) {
			return false;
		}

		int[] pos = io.getPosition(board.getCurrentPlayer());

		if (board.placeStone(pos[0], pos[1])) {
			io.printBoard(board);

			if (board.checkWin(pos[0], pos[1])) {
				winner = board.getCurrentPlayer();
				return false;
			}
			board.switchPlayer();
		} else {
			io.inputError(pos);
		}
		return true;
	}

	public void showResult() {
		io.showResult(winner);
	}
}
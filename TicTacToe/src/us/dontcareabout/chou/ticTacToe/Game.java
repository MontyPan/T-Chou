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

		int input = io.getPosition(board.getCurrentPlayer());
		int[] pos = new int[]{(input - 1) / Board.N, (input - 1) % Board.N};

		if (board.placeStone(pos[0], pos[1])) {
			io.printBoard(board);

			if (board.checkWin(pos[0], pos[1])) {
				winner = board.getCurrentPlayer();
				return false;
			}
			board.switchPlayer();
		} else {
			io.inputError(input);
		}
		return true;
	}

	public void showResult() {
		io.showResult(winner);
	}
}
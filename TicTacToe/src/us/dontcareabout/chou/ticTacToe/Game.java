package us.dontcareabout.chou.ticTacToe;

import us.dontcareabout.chou.ticTacToe.exception.IllegalPositionException;
import us.dontcareabout.chou.ticTacToe.exception.UnexceptedInputException;

public class Game {
	private Board board = new Board();
	private GameIO io = new GameIO();
	private Boolean winner;

	public Game() {
		io.printBoard(board);
	}

	public void playGame() {
		int input = 0;
		int[] pos;

		while (board.hasEmpty()) {
			try {
				input = io.getPosition(board.getCurrentPlayer());
			} catch (UnexceptedInputException e) {
				io.inputError();
				continue;
			} catch (IllegalPositionException e) {
				io.inputError();
				continue;
			}

			pos = new int[]{(input - 1) / Board.N, (input - 1) % Board.N};

			if (!board.placeStone(pos[0], pos[1])) {
				io.inputError(input);
				continue;
			}

			io.printBoard(board);

			if (board.checkWin(pos[0], pos[1])) {
				winner = board.getCurrentPlayer();
				return;
			}

			board.switchPlayer();
		}
	}

	public void showResult() {
		io.showResult(winner);
	}
}
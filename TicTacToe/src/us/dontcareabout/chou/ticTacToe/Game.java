package us.dontcareabout.chou.ticTacToe;

import us.dontcareabout.chou.ticTacToe.InputEvent.InputHandler;
import us.dontcareabout.chou.ticTacToe.exception.IllegalPositionException;
import us.dontcareabout.chou.ticTacToe.exception.UnexceptedInputException;

public class Game {
	private Board board = new Board();
	private GameIO io = new GameIO();
	private boolean endFlag;

	public Game() {
		io.printBoard(board);

		EventCenter.addInputHandler(new InputHandler() {
			@Override
			public void onInput(InputEvent event) {
				process(event.value);
			}
		});
	}

	public void playGame() {
		endFlag = false;

		while (!endFlag && board.hasEmpty()) {
			try {
				//注意：requirePosition() 幣需要有能暫停程式運作的能力
				io.requirePosition(board.getCurrentPlayer());
			} catch (UnexceptedInputException e) {
				io.inputError();
				continue;
			} catch (IllegalPositionException e) {
				io.inputError();
				continue;
			}
		}

		if (!endFlag) {
			io.showTie();
		}
	}

	private void process(int input) {
		int[] pos = new int[]{(input - 1) / Board.N, (input - 1) % Board.N};

		if (!board.placeStone(pos[0], pos[1])) {
			io.inputError(input);
			return;
		}

		io.printBoard(board);

		if (board.checkWin(pos[0], pos[1])) {
			io.showWinner(board.getCurrentPlayer());
			endFlag = true;
			return;
		}

		board.switchPlayer();
	}
}
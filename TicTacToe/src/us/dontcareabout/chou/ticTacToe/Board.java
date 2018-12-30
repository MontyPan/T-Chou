package us.dontcareabout.chou.ticTacToe;

public class Board {
	public static final int N = 3;
	char[][] playBoard = new char[N][N];

	public Board() {
		System.out.println("Board");
		initBoard();
	}

	public void initBoard() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				playBoard[i][j] = ' ';
			}
		}
	}

	/**
	 * Print TicTacToe board
	 */
	public void printBoard() {

		// create inputBoard
		char[][] inputBoard = new char[N][N];

		for (int idx1 = 0; idx1 < N; idx1++) {
			for (int idx2 = 0; idx2 < N; idx2++) {
				int number = idx1 * N + idx2 + 1;
				inputBoard[idx1][idx2] = (char) (number + '0');
			}
		}

		// print out
		for (int idx = 0; idx < N; idx++) {
			String board1 = String.valueOf(playBoard[idx]);
			String board2 = String.valueOf(inputBoard[idx]);
			String spaces = "       ";

			System.out.println(board1 + spaces + board2);
		}
	}
}

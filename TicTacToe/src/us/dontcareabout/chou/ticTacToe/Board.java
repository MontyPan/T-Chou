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
}

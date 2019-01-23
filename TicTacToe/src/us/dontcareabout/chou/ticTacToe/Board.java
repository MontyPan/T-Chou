package us.dontcareabout.chou.ticTacToe;

public class Board {
	public static final int N = 3;
	Boolean[][] playBoard = new Boolean[N][N];
	boolean player = true;

	public Board() {
		System.out.println("Board");
		initBoard();
	}

	public void initBoard() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				playBoard[i][j] = null;
			}
		}
	}

	/**
	 * @param i the last input of row
	 * @param j the last input of column
	 * @return true if the last input makes the player win.
	 */
	public boolean checkWin(int i, int j) {
		Boolean marker = playBoard[i][j];
		int[] count2Three = {0, 0, 0, 0};

		for (int idx = 0; idx < N; idx++) {
			if (playBoard[i][idx] == marker) {
				count2Three[0] += 1;
			}
			if (playBoard[idx][j] == marker) {
				count2Three[1] += 1;
			}
			if (playBoard[idx][idx] == marker) {
				count2Three[2] += 1;
			}
			if (playBoard[idx][N - 1 - idx] == marker) {
				count2Three[3] += 1;
			}
		}

		for (int idx = 0; idx < count2Three.length; idx++) {
			if (count2Three[idx] == N) {
				return true;
			}
		}
		return false;
	}

	/**
	 * place stones on position i, j.
	 *
	 * @param i number of row
	 * @param j number of column
	 * @return true if the position i, j is empty where stones can be placed.
	 */
	public boolean placeStone(int i, int j) {
		boolean marker = player;

		if (playBoard[i][j] == null) {
			playBoard[i][j] = marker;
			return true;
		} else {
			System.out.println("The position " + i + ", " + j + " is not available!");
			return false;
		}
	}
}

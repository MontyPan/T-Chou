package us.dontcareabout.chou.ticTacToe;

public class Board {
	public static final int N = 3;
	private Boolean[][] playBoard = new Boolean[N][N];
	private boolean player = true;

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
			return false;
		}
	}

	/**
	 * @return the status of position i, j
	 */
	public Boolean getStatus(int i, int j) {
		return playBoard[i][j];
	}

	/**
	 * @return current player
	 */
	public boolean getCurrentPlayer() {
		return player;
	}

	public void switchPlayer() {
		player = !player;
	}
}

package us.dontcareabout.chou.ticTacToe;

public class Main {

	public static void main(String[] args) {
		Board board = new Board();
		Game game = new Game(board);

		boolean isEnd = false;
		while (!isEnd) {
			isEnd = game.playGame();
		}
	}
}

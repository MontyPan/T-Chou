package us.dontcareabout.chou.ticTacToe;

public class Main {

	public static void main(String[] args) {
		Game game = new Game();

		boolean isEnd = false;
		while (!isEnd) {
			isEnd = game.playGame();
		}
	}
}

package us.dontcareabout.chou.ticTacToe;

public class Main {

	public static void main(String[] args) {
		Game game = new Game();

		boolean isEnd = true;
		while (isEnd) {
			isEnd = game.round();
		}

		game.showResult();
	}
}

package us.dontcareabout.chou.ticTacToe.exception;

@SuppressWarnings("serial")
public class IllegalPositionException extends Exception {

	public final int position;

	public IllegalPositionException(int position) {
		this.position = position;
	}
}

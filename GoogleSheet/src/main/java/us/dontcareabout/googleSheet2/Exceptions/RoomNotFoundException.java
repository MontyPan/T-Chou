package us.dontcareabout.googleSheet2.Exceptions;

public class RoomNotFoundException extends RuntimeException {
	public final String room;

	public RoomNotFoundException(String room) {
		super(room + " not found!");
		this.room = room;
	}
}

package us.dontcareabout.googleSheet2.Exceptions;

public class ExhibitionNotFoundException extends RuntimeException {
	public final String name;

	public ExhibitionNotFoundException(String name) {
		super(name + " not found!");
		this.name = name;
	}
}

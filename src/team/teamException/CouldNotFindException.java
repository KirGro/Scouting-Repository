package teamAPI.teamExceptionAPI;

public class CouldNotFindException extends Exception {
	public CouldNotFindException() { super(); }
	public CouldNotFindException(String message) { super(message); }
	public CouldNotFindException(String message, Throwable cause) { super(message, cause); }
	public CouldNotFindException(Throwable cause) { super(cause); }
}

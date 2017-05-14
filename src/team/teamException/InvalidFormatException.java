package team.teamException;

public class InvalidFormatException extends Exception {
	public InvalidFormatException() { super(); }
	public InvalidFormatException(String message) { super(message); }
	public InvalidFormatException(String message, Throwable cause) { super(message, cause); }
	public InvalidFormatException(Throwable cause) { super(cause); }
}

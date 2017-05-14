package team.teamException;

public class InvalidNameNumberException extends Exception {
	public InvalidNameNumberException() { super(); }
	public InvalidNameNumberException(String message) { super(message); }
	public InvalidNameNumberException(String message, Throwable cause) { super(message, cause); }
	public InvalidNameNumberException(Throwable cause) { super(cause); }
}

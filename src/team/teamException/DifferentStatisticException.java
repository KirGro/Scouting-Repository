package team.teamException;

public class DifferentStatisticException extends Exception {
	public DifferentStatisticException() { super(); }
	public DifferentStatisticException(String message) { super(message); }
	public DifferentStatisticException(String message, Throwable cause) { super(message, cause); }
	public DifferentStatisticException(Throwable cause) { super(cause); }
}

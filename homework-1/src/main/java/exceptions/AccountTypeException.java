package exceptions;

public class AccountTypeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AccountTypeException(String message) {
		super(message);
	}
}

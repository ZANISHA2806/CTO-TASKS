package exception;


public class AuthenticationException extends CentralBankAPIException {

	public AuthenticationException(String message) {
		super(message);
	}
}

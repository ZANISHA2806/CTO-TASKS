package exception;

public class APICircuitBreakerException extends RuntimeException {
	
	public APICircuitBreakerException(String message) {
		super(message);
	}

}

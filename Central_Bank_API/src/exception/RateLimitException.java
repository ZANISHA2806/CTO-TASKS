package exception;

public class RateLimitException extends CentralBankAPIException{
	private final int retry;
	public RateLimitException(String message, int retry) {
		super(message);
		this.retry=retry;
	}

	public int getRetry() {
		return retry;
	}
}

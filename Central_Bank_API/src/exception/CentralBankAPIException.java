package exception;

public class CentralBankAPIException extends Exception {
	
	public CentralBankAPIException(String message) {
		super(message);
		
	}
	
	public CentralBankAPIException(String message, Throwable cause) {
		super(message, cause);
	}

}

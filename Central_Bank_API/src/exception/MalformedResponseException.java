package exception;

public class MalformedResponseException extends CentralBankAPIException{
	
	public MalformedResponseException(String message) {
		super(message);
	}

}

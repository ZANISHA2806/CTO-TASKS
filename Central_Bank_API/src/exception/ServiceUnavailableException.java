package exception;

public class ServiceUnavailableException extends CentralBankAPIException{
public ServiceUnavailableException(String message) {
	super(message);
}
}

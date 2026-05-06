package exception;

public class IneligibleShareholderException extends DividendException {
	private String shareholderId;
	private String reason;
	
	public IneligibleShareholderException(String shareholderId,String reason) {
		super(reason);
		this.shareholderId=shareholderId;
		this.reason=reason;
	}
public String getshareholderId() {
	return shareholderId;
}
public String getReason() {
	return reason;
}
}

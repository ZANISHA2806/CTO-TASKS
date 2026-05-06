package exception;

public class CardSuspendedException extends CardLoadException{
	private final String cardId;
	private final String reason;
	
	public CardSuspendedException(String cardId,String reason) {
		super("Card " + cardId + " is suspended: " + reason);
		this.cardId=cardId;
		this.reason=reason;
		
	}
	
	public String getCardid() {
		return cardId;
		
	}
	public String getReason() {
		return reason;
	}

}

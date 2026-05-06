package exception;

public class TaxDeductionException extends Exception {
	
	private String shareholderId;
	private double attemptedtds;

	public TaxDeductionException(String shareholderId,double attemptedtds) {
		super("TDS deduction of Rs." + attemptedtds + " failed");
		this.shareholderId=shareholderId;
		this.attemptedtds=attemptedtds;
	}
	public String getShareholderId() {
		return shareholderId;
	}
	public double getAttemptedtds() {
		return attemptedtds;
	}
}

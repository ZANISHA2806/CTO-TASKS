package exception;

public class DividendProcessingHaltException extends RuntimeException {
	private int failurecount;
	private int totalprocess;
	private double failurerate;
	
	public DividendProcessingHaltException(int failurecount,int totalprocess,double failurerate) {
		super("DIVIDEND HALT triggered: failure rate "
                + (failurerate * 100) + "% > threshold 30%");
		this.failurecount=failurecount;
		this.failurerate=failurerate;
		this.totalprocess=totalprocess;
	}

	public int getFailureCount() {
        return failurecount;
    }

    public int getTotalProcessed() {
        return totalprocess;
    }

    public double getFailureRate() {
        return failurerate;
    }
}

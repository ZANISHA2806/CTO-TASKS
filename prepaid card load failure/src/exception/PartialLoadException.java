package exception;

public class PartialLoadException extends Exception{
	private final double loadedamount;
	private final double failedamount;
	
	public PartialLoadException(double loadedamount,double failedamount) {
		
		super("loaded amount rs."+loadedamount+"failed amount rs."+failedamount);
		this.failedamount=failedamount;
		this.loadedamount=loadedamount;
	}
	public double getLoadedamount() {
		return loadedamount;
	}
	public double getFailedamount() {
		return failedamount;
	}
	

}

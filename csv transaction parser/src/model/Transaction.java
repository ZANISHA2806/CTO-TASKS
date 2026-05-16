package model;


public class Transaction {

	private String tid;
	private String acc;
	private Transactiontype type;
	private double amount;
	private String currency;
	private String valuedate;
	private String status;
	private double INRamount;
	
	public Transaction(String tid,String acc,Transactiontype type,double amount,String currency,String valuedate,String status) {
		
		this.tid=tid;
		this.acc=acc;
		this.type=type;
		this.amount=amount;
		this.valuedate=valuedate;
		this.currency=currency;
		this.status=status;
	}
	public String getTid() {
		return tid;
	}
	public String getAcc() {
		return acc;
	}
	
	public Transactiontype getType() {
		return type;
	}
	public Double getAmount() {
		return amount; 
	}
	public String getValuedate() {
		return valuedate;
	}
	public String getCurrency() {
		return currency;
	}
	public String getStatus() {
		return status;
	}
	   public double getInrAmount() {
	        return INRamount;
	    }

	    public void setInrAmount(double inrAmount) {
	        this.INRamount = inrAmount;
	    }

	
	@Override
	public String toString() {
		
		return ("TNX_ID : "+tid+"| ACCOUNT: "+acc+"| TYPE :"+type+"| AMOUNT:"+amount+"| VALUEDATE:"+valuedate+"| CURRENCY:"+currency+"| STATUS:"+status+   " | INR_AMOUNT: " + INRamount);
	}
}

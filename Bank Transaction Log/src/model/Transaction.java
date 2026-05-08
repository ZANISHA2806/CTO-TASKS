package model;

import java.util.Date;
public class Transaction {

	private String tid;
	private String acc;
	private Transactiontype type;
	private double amount;
	private Date date;
	
	
	public Transaction(String tid,String acc,Transactiontype type,double amount,Date date) {
		
		this.tid=tid;
		this.acc=acc;
		this.type=type;
		this.amount=amount;
		this.date=date;
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
	public Date getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		
		return ("TNX_ID : "+tid+"| ACCOUNT: "+acc+"| TYPE :"+type+"| AMOUNT:"+amount+"| DATE:"+date);
	}
}

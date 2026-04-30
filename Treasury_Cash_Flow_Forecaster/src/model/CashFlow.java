package model;

public class CashFlow {

	private String flowid;
	private String date;
	private FlowType type;
	private Category category;
	private double amount;
	private String counterparty;
	
	public CashFlow(String flowid,String date,FlowType type,Category category,double amount,String counterparty) {
		this.flowid=flowid;
		this.date=date;
		this.amount=amount;
		this.category=category;
		this.counterparty=counterparty;
		this.type=type;
		
	}
	
	public String getFlowid() {
		return flowid;
	}
	
	public String getDate() {
		return date;
	}
	public FlowType getType() {
		return type;
	}
	public Category getCategory() {
		return category;
	}
	public String getCounterparty() {
		return counterparty;
	}
	public double getAmount() {
		return amount;
	}
	
	@Override
	public String toString() {
		return flowid+" | "+ date+" | "+type+" | "+category+" | "+counterparty+" | "+amount;
	}
}

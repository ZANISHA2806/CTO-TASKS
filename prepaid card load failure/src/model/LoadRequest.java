package model;

public class LoadRequest {
private String cardId;
private String sourceVPA;
private double amount;

public LoadRequest(String cardId,String sourceVPA,double amount) {
	this.amount=amount;
	this.cardId=cardId;
	this.sourceVPA=sourceVPA;
}
public String getCardId() {
	return cardId;
}
public double getamount() {
	return amount;
}
public String getsourceVPA() {
	return sourceVPA;
}
}

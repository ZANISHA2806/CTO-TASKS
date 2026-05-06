package model;

public class Card {

    private String cardId;
    private boolean isSuspended;
    private double dailyTotal;

    public Card(String cardId, boolean isSuspended, double dailyTotal) {
        this.cardId = cardId;
        this.isSuspended = isSuspended;
        this.dailyTotal = dailyTotal;
    }

    public Card(String cardId, boolean isSuspended) {
        this.cardId = cardId;
        this.isSuspended = isSuspended;
        this.dailyTotal = 0.0;
    }
    public String getCardId() {
        return cardId;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public double getDailyTotal() {
        return dailyTotal;
    }

    public void addToDailyTotal(double amount) {
        this.dailyTotal += amount;
    }

    public void setSuspended(boolean suspended) {
        this.isSuspended = suspended;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId='" + cardId + '\'' +
                ", isSuspended=" + isSuspended +
                ", dailyTotal=" + dailyTotal +
                '}';
    }
}
package model;

public class Transaction {

    private String transactionId;
    private double amount;
    private long timestamp;
    private byte typeCode;

    public Transaction(String transactionId,
                       double amount,
                       long timestamp,
                       byte typeCode) {

        this.transactionId = transactionId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.typeCode = typeCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public byte getTypeCode() {
        return typeCode;
    }
    @Override
    public String toString() {

        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", typeCode=" + typeCode +
                '}';
    }
}
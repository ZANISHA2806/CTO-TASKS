package model;

public class DividendResult {

    private String shareholderId;
    private double grossAmount;
    private double tdsAmount;
    private double netAmount;
    private Status status;
    private String message;

    public DividendResult(String shareholderId,
                          double grossAmount,
                          double tdsAmount,
                          double netAmount,
                          Status status,
                          String message) {

        this.shareholderId = shareholderId;
        this.grossAmount = grossAmount;
        this.tdsAmount = tdsAmount;
        this.netAmount = netAmount;
        this.status = status;
        this.message = message;
    }

  
    public String getShareholderId() {
        return shareholderId;
    }

    public double getGrossAmount() {
        return grossAmount;
    }

    public double getTdsAmount() {
        return tdsAmount;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

   
    @Override
    public String toString() {
        return "DividendResult{" +
                "shareholderId='" + shareholderId + '\'' +
                ", grossAmount=" + grossAmount +
                ", tdsAmount=" + tdsAmount +
                ", netAmount=" + netAmount +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
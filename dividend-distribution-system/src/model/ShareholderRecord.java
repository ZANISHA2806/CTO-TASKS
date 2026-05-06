package model;

public class ShareholderRecord {

    private String shareholderId;
    private double sharesHeld;
    private boolean isEligible;
    private boolean isBankValid;
    private boolean isTdsFail;

    public ShareholderRecord(String shareholderId,
                             double sharesHeld,
                             boolean isEligible,
                             boolean isBankValid,
                             boolean isTdsFail) {

        this.shareholderId = shareholderId;
        this.sharesHeld = sharesHeld;
        this.isEligible = isEligible;
        this.isBankValid = isBankValid;
        this.isTdsFail = isTdsFail;
    }

   
    public String getShareholderId() {
        return shareholderId;
    }

    public double getSharesHeld() {
        return sharesHeld;
    }

    public boolean isEligible() {
        return isEligible;
    }

    public boolean isBankValid() {
        return isBankValid;
    }

    public boolean isTdsFail() {
        return isTdsFail;
    }
}
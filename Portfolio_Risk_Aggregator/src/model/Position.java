package model;

public class Position {

    private String positionId;
    private String instrument;
    private Asset asset;
    private int quantity;
    private double marketvalue;
    private double costbasis;

    
    public Position(String positionId,Asset asset,String instrument,int quantity,double marketvalue,double costbasis) {

        this.positionId = positionId;
        this.asset = asset;
        this.instrument = instrument;
      
        this.quantity = quantity;
        this.marketvalue = marketvalue;
        this.costbasis = costbasis;
    }


  

	public String getPositionId() {
        return positionId;
    }

    public String getInstrument() {
        return instrument;
    }

    public Asset getAsset() {
        return asset;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getMarketvalue() {
        return marketvalue;
    }

    public double getCostbasis() {
        return costbasis;
    }

    public double getPositionPnL() {
        return marketvalue - costbasis;
    }

    @Override
    public String toString() { 
        return "Position{" +
                "positionId='" + positionId + '\'' +
                ", instrument='" + instrument + '\'' +
                ", asset=" + asset +
                ", quantity=" + quantity +
                ", marketvalue=" + marketvalue +
                ", costBasis=" + costbasis +
                ", pnl=" + getPositionPnL() +
                '}';
    }
}
package service;

import model.Asset;
import model.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class RiskAggregator {

    private ArrayList<Position> allPositions;
    private HashMap<Asset, Double> allocationLimits;
    private Logger logger;

    public RiskAggregator(Logger logger) {

        this.logger = logger;

        allPositions = new ArrayList<>();

        allocationLimits = new HashMap<>();

        allocationLimits.put(Asset.EQUITY,0.60);
        allocationLimits.put(Asset.BOND,0.30);
        allocationLimits.put(Asset.COMMODITY,0.15);
        allocationLimits.put(Asset.FOREX,0.10);
    }

    public void addPosition(Position position) {
        allPositions.add(position);
        logger.info("Added " + position.getInstrument());
    }

    public double getTotalPortfolioValue() {

        double total=0;

        for(Position position : allPositions) {
            total += position.getMarketvalue();
        }

        return total;
    }

    public Map<Asset,Double> getExposureByClass(){

        Map<Asset,Double> exposures=
                new HashMap<>();

        for(Position position:allPositions){

            Asset asset=
                    position.getAsset();

            exposures.put(
                    asset,
                    exposures.getOrDefault(
                            asset,
                            0.0)
                            + position.getMarketvalue()
            );
        }

        return exposures;
    }

    public Position getLargestPosition(){

        if(allPositions.isEmpty()){
            return null;
        }

        Position largest=
                allPositions.get(0);

        for(Position position:allPositions){

            if(position.getMarketvalue() >
                    largest.getMarketvalue()){

                largest=position;
            }
        }

        return largest;
    }

    public void checkBreaches(){

        double total=
                getTotalPortfolioValue();

        Map<Asset,Double> exposures=
                getExposureByClass();

        for(Map.Entry<Asset,Double> entry
                : exposures.entrySet()){

            Asset asset=
                    entry.getKey();

            double exposure=
                    entry.getValue();

            double ratio=
                    exposure/total;

            double limit=
                    allocationLimits.get(asset);

            if(ratio>limit){

                logger.warning(
                        asset+
                        " breach detected"
                );
            }

            else{

                logger.info(
                        asset+
                        " within limit"
                );
            }

        }

    }

    public double getUnrealisedPnL(){

        double pnl=0;

        for(Position position:allPositions){

            pnl +=
                    position.getMarketvalue()
                    -
                    position.getCostbasis();

        }

        return pnl;
    }

}
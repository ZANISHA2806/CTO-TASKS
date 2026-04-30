package main;

import data.PortfolioDataLoader;
import model.Asset;
import model.Position;
import service.RiskAggregator;
import config.LoggingConfig;
import util.FormatterUtil;

import java.util.Map;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        Logger logger =
                LoggingConfig.getLogger();

        logger.info(
                "Portfolio Risk Aggregator Started"
        );

        RiskAggregator aggregator =
                new RiskAggregator(logger);

        PortfolioDataLoader
                .loadSamplePositions(
                        aggregator
                );

        double totalPortfolioValue =
                aggregator.getTotalPortfolioValue();

        logger.info(
                "Total Portfolio Value : "
                + FormatterUtil.formatCurrency(
                        totalPortfolioValue
                )
        );

        logger.info(
                "Exposure By Asset Class"
        );

        Map<Asset, Double> exposures =
                aggregator.getExposureByClass();

        for (
                Map.Entry<Asset, Double> entry
                        : exposures.entrySet()
        ) {

            double percentage =
                    entry.getValue()
                    / totalPortfolioValue;

            logger.info(
                    entry.getKey()
                    + " : "
                    + FormatterUtil.formatCurrency(
                            entry.getValue()
                    )
                    + " ("
                    + FormatterUtil.formatPercentage(
                            percentage
                    )
                    + ")"
            );
        }

        Position largestPosition =
                aggregator.getLargestPosition();

        if (largestPosition != null) {

            logger.info(
                    "Largest Position : "
                    + largestPosition.getInstrument()
                    + " "
                    + largestPosition.getAsset()
                    + " "
                    + FormatterUtil.formatCurrency(
                            largestPosition.getMarketvalue()
                    )
            );
        }

        logger.info(
                "Checking allocation breaches"
        );

        aggregator.checkBreaches();

        double pnl =
                aggregator.getUnrealisedPnL();

        logger.info(
                "Unrealised PnL : "
                + FormatterUtil.formatPnL(pnl)
        );

        logger.info(
                "Portfolio Risk Aggregator Completed"
        );
    }
}
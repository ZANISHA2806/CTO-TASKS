package data;

import model.Asset;
import model.Position;
import service.RiskAggregator;

public class PortfolioDataLoader {

    public static void loadSamplePositions(
            RiskAggregator aggregator) {

        aggregator.addPosition(
                new Position(
                        "P101",
                        Asset.EQUITY,
                        "RELIANCE",
                        1000,
                        1800000.00,
                        1600000.00
                )
        );

        aggregator.addPosition(
                new Position(
                        "P102",
                        Asset.EQUITY,
                        "TCS",
                        800,
                        2500000.00,
                        2300000.00
                )
        );

        aggregator.addPosition(
                new Position(
                        "P103",
                        Asset.EQUITY,
                        "INFY",
                        700,
                        2200000.00,
                        2100000.00
                )
        );

        aggregator.addPosition(
                new Position(
                        "P201",
                        Asset.BOND,
                        "GOV_BOND",
                        500,
                        900000.00,
                        850000.00
                )
        );

        aggregator.addPosition(
                new Position(
                        "P202",
                        Asset.BOND,
                        "CORP_BOND",
                        600,
                        700000.00,
                        680000.00
                )
        );

        aggregator.addPosition(
                new Position(
                        "P203",
                        Asset.BOND,
                        "TREASURY",
                        400,
                        600000.00,
                        590000.00
                )
        );

        aggregator.addPosition(
                new Position(
                        "P301",
                        Asset.COMMODITY,
                        "GOLD",
                        100,
                        300000.00,
                        350000.00
                )
        );

        aggregator.addPosition(
                new Position(
                        "P302",
                        Asset.COMMODITY,
                        "SILVER",
                        200,
                        250000.00,
                        220000.00
                )
        );

        aggregator.addPosition(
                new Position(
                        "P303",
                        Asset.COMMODITY,
                        "CRUDE",
                        150,
                        250000.00,
                        230000.00
                )
        );

        aggregator.addPosition(
                new Position(
                        "P401",
                        Asset.FOREX,
                        "USDINR",
                        10000,
                        200000.00,
                        190000.00
                )
        );

        aggregator.addPosition(
                new Position(
                        "P402",
                        Asset.FOREX,
                        "EURINR",
                        8000,
                        150000.00,
                        145000.00
                )
        );

        aggregator.addPosition(
                new Position(
                        "P403",
                        Asset.FOREX,
                        "JPYINR",
                        9000,
                        150000.00,
                        155000.00
                )
        );
    }

}
package main;

import repository.CashFlowRepository;
import service.*;
import util.CashFlowSeeder;
import util.Log;

import model.CashFlow;
import model.ForecastSummary;

import java.util.List;
import java.util.logging.Logger;

public class Main {

    private static final Logger log =
            Log.getLogger();


    public static void main(String[] args) {

        log.info(
          "Starting Treasury Cash Flow Forecaster"
        );


        
        CashFlowRepository repo =
                new CashFlowRepository();



        
        List<CashFlow> flows =
                CashFlowSeeder.seedData();

        for(CashFlow flow : flows) {
            repo.addFlow(flow);
        }

        log.info(
          "Seed data loaded successfully"
        );



      
        DailyFlowCalculator dailyCalc =
                new DailyFlowCalculator(repo);

        BalanceCalculator balanceCalc =
                new BalanceCalculator(repo);

        LiquidityAnalyzer liquidityAnalyzer =
                new LiquidityAnalyzer(
                        repo,
                        dailyCalc,
                        balanceCalc
                );

        ForecastSummaryService summaryService =
                new ForecastSummaryService(
                        repo,
                        dailyCalc,
                        balanceCalc
                );



    
        log.info(
           "----- Daily Net Flows -----"
        );

        for(String date :
                repo.getAllFlows().keySet()) {

            log.info(
                String.format(
                  "%s Net Flow: Rs.%,.2f",
                  date,
                  dailyCalc.getDailyNetFlow(
                      date
                  )
                )
            );
        }



        
        String fromDate = "2024-03-01";
        String toDate   = "2024-03-07";

        double balance =
                balanceCalc.getCumulativeBalance(
                        fromDate,
                        toDate
                );

        log.info(
            String.format(
               "Cumulative Balance %s to %s: Rs.%,.2f",
                fromDate,
                toDate,
                balance
            )
        );


       
        log.info(
          "----- Liquidity Alerts -----"
        );

        liquidityAnalyzer
                .getLiquidityAlerts(
                        500000
                )
                .forEach(
                     log::warning
                );



      
        log.info(
          "----- Forecast Summary -----"
        );

        ForecastSummary summary =
                summaryService
                  .generateSummary();

        log.info(
            summary.toString()
        );



        if(liquidityAnalyzer
             .hasLiquidityRisk(
                 500000
             )) {

            log.warning(
              "Liquidity Risk Detected"
            );
            liquidityAnalyzer
            .getLiquidityAlerts(500000)
            .forEach(log::warning);
        }
        else{

            log.info(
              "Liquidity Position Healthy"
            );
        }


        log.info(
           "Cash Flow Forecast Completed"
        );
    }
}
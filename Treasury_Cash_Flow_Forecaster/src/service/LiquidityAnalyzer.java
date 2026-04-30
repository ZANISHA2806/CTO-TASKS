package service;

import repository.CashFlowRepository;

import java.util.ArrayList;
import java.util.List;

public class LiquidityAnalyzer {

    private final CashFlowRepository repository;
    private final DailyFlowCalculator dailyFlowCalculator;
    private final BalanceCalculator balanceCalculator;


    public LiquidityAnalyzer(
            CashFlowRepository repository,
            DailyFlowCalculator dailyFlowCalculator,
            BalanceCalculator balanceCalculator) {

        this.repository = repository;
        this.dailyFlowCalculator = dailyFlowCalculator;
        this.balanceCalculator = balanceCalculator;
    }

    public List<String> getLiquidityAlerts(
            double minimumBalance) {

        List<String> alerts =
                new ArrayList<>();


        if(repository.getAllFlows().isEmpty()) {
            return alerts;
        }

        String firstDate =
                repository.getAllFlows().firstKey();



       
        for(String currentDate :
                repository.getAllFlows().keySet()) {


          
            double runningBalance =
                    balanceCalculator
                    .getCumulativeBalance(
                        firstDate,
                        currentDate
                    );


            if(runningBalance < minimumBalance) {

                alerts.add(
                    String.format(
                      "LIQUIDITY ALERT: %s balance Rs.%,.2f below minimum Rs.%,.2f",
                       currentDate,
                       runningBalance,
                       minimumBalance
                    )
                );
            }
        }

        return alerts;
    }



    public void printLiquidityAlerts(
            double minimumBalance) {

        List<String> alerts =
                getLiquidityAlerts(
                    minimumBalance
                );

        if(alerts.isEmpty()) {

            System.out.println(
              "No liquidity alerts."
            );

            return;
        }

        System.out.println(
            "\nLiquidity Alerts:"
        );

        for(String alert : alerts) {
            System.out.println(alert);
        }
    }



 
    public boolean hasLiquidityRisk(
            double minimumBalance) {

        return !getLiquidityAlerts(
                minimumBalance
               ).isEmpty();
    }



   
    public void printLiquidityTimeline(
            double minimumBalance) {

        if(repository.getAllFlows().isEmpty()) {
            return;
        }

        String firstDate =
            repository.getAllFlows().firstKey();


        System.out.println(
          "\nLiquidity Timeline:"
        );


        for(String date :
            repository.getAllFlows().keySet()) {

            double dailyNet =
                dailyFlowCalculator
                .getDailyNetFlow(date);


            double runningBalance =
                balanceCalculator
                .getCumulativeBalance(
                    firstDate,
                    date
                );


            String status =
              runningBalance < minimumBalance
                  ? " ALERT"
                  : " OK";


            System.out.printf(
                "%s | Net: Rs.%,.2f | Balance: Rs.%,.2f | %s%n",
                date,
                dailyNet,
                runningBalance,
                status
            );
        }
    }
}
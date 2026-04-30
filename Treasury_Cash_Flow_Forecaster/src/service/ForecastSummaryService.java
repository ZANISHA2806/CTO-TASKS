package service;

import model.Category;
import model.ForecastSummary;
import repository.CashFlowRepository;

import java.util.Map;

public class ForecastSummaryService {

    private final CashFlowRepository repository;
    private final DailyFlowCalculator dailyFlowCalculator;
    private final BalanceCalculator balanceCalculator;


    public ForecastSummaryService(
            CashFlowRepository repository,
            DailyFlowCalculator dailyFlowCalculator,
            BalanceCalculator balanceCalculator) {

        this.repository = repository;
        this.dailyFlowCalculator = dailyFlowCalculator;
        this.balanceCalculator = balanceCalculator;
    }



    public ForecastSummary generateSummary() {

        double totalInflows = 0.0;
        double totalOutflows = 0.0;


      
        for(String date :
                repository.getAllFlows().keySet()) {

            totalInflows +=
                dailyFlowCalculator
                .getTotalInflows(date);

            totalOutflows +=
                dailyFlowCalculator
                .getTotalOutflows(date);
        }



       
        double netFlow = 0.0;

        if(!repository.getAllFlows().isEmpty()) {

            String firstDate =
                repository.getAllFlows().firstKey();

            String lastDate =
                repository.getAllFlows().lastKey();

            netFlow =
                balanceCalculator
                .getCumulativeBalance(
                    firstDate,
                    lastDate
                );
        }



       
        Map<Category, Double> categoryTotals =
                repository.getCategoryTotals();



        return new ForecastSummary(
                totalInflows,
                totalOutflows,
                netFlow,
                categoryTotals
        );
    }



   
    public void printForecastSummary() {

        ForecastSummary summary =
                generateSummary();

        System.out.println(summary);
    }



    
    public boolean isNetPositive() {

        return generateSummary()
                .getTotalnetflow() > 0;
    }
}
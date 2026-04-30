package service;

import repository.CashFlowRepository;
import service.DailyFlowCalculator;
import java.util.Map;

public class BalanceCalculator {

    private final CashFlowRepository repository;
    private final DailyFlowCalculator dailyFlowCalculator;

    public BalanceCalculator(CashFlowRepository repository) {
        this.repository = repository;
        this.dailyFlowCalculator = new DailyFlowCalculator(repository);
    }

  
    public double getCumulativeBalance(String startDate, String endDate) {

        validateDates(startDate, endDate);

        double cumulativeBalance = 0.0;

             Map<String, ?> dateRange =
                repository.getFlowsInRange(startDate, endDate);

        if (dateRange.isEmpty()) {
            return 0.0;
        }

        for (String date : dateRange.keySet()) {
            cumulativeBalance +=
                    dailyFlowCalculator.getDailyNetFlow(date);
        }

        return cumulativeBalance;
    }

    
    public void printRunningBalance(String startDate, String endDate) {

        validateDates(startDate, endDate);

        double runningBalance = 0.0;

        Map<String, ?> dateRange =
                repository.getFlowsInRange(startDate, endDate);

        if (dateRange.isEmpty()) {
            System.out.println("No flows found in given range.");
            return;
        }

        System.out.println("\nRunning Balance:");

        for (String date : dateRange.keySet()) {

            double dailyNet =
                    dailyFlowCalculator.getDailyNetFlow(date);

            runningBalance += dailyNet;

            System.out.printf(
                    "%s -> Daily Net: Rs.%,.2f | Running Balance: Rs.%,.2f%n",
                    date,
                    dailyNet,
                    runningBalance
            );
        }
    }

    
    private void validateDates(String startDate, String endDate) {

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException(
                    "Dates cannot be null"
            );
        }

        if (startDate.compareTo(endDate) > 0) {
            throw new IllegalArgumentException(
                    "Start date cannot be after end date"
            );
        }
    }
}
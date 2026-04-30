package service;

import model.CashFlow;
import model.FlowType;
import repository.CashFlowRepository;

import java.util.List;

public class DailyFlowCalculator {

    private final CashFlowRepository repository;

    public DailyFlowCalculator(CashFlowRepository repository) {
        this.repository = repository;
    }

    
    public double getDailyNetFlow(String date) {

        List<CashFlow> flows =
                repository.getFlowsByDate(date);

        if (flows == null || flows.isEmpty()) {
            return 0.0;
        }

        double netFlow = 0.0;

        for (CashFlow flow : flows) {

            if (flow.getType() == FlowType.INFLOW) {
                netFlow += flow.getAmount();
            }
            else {
                netFlow -= flow.getAmount();
            }
        }

        return netFlow;
    }


   
    public double getTotalInflows(String date) {

        List<CashFlow> flows =
                repository.getFlowsByDate(date);

        double inflows = 0.0;

        if (flows == null) {
            return inflows;
        }

        for (CashFlow flow : flows) {
            if (flow.getType() == FlowType.INFLOW) {
                inflows += flow.getAmount();
            }
        }

        return inflows;
    }


    
    public double getTotalOutflows(String date) {

        List<CashFlow> flows =
                repository.getFlowsByDate(date);

        double outflows = 0.0;

        if (flows == null) {
            return outflows;
        }

        for (CashFlow flow : flows) {
            if (flow.getType() == FlowType.OUTFLOW) {
                outflows += flow.getAmount();
            }
        }

        return outflows;
    }
}
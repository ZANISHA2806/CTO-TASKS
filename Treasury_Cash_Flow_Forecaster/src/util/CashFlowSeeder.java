package util;

import java.util.ArrayList;
import java.util.List;

import model.CashFlow;
import model.Category;
import model.FlowType;

public class CashFlowSeeder {

    public static List<CashFlow> seedData() {

        List<CashFlow> flows =
                new ArrayList<>();


        
        flows.add(new CashFlow(
                "F001",
                "2024-03-01",
                FlowType.INFLOW,
                Category.RECEIVABLE,
                1500000,
                "Client A"
        ));

        flows.add(new CashFlow(
                "F002",
                "2024-03-01",
                FlowType.OUTFLOW,
                Category.PAYABLE,
                300000,
                "Payroll"
        ));


      
        flows.add(new CashFlow(
                "F003",
                "2024-03-02",
                FlowType.INFLOW,
                Category.RECEIVABLE,
                700000,
                "Client B"
        ));

        flows.add(new CashFlow(
                "F004",
                "2024-03-02",
                FlowType.OUTFLOW,
                Category.INVESTMENT,
                900000,
                "Short Term Fund"
        ));


     
        flows.add(new CashFlow(
                "F005",
                "2024-03-03",
                FlowType.INFLOW,
                Category.LOAN_REPAYMENT,
                600000,
                "Loan Recovery"
        ));

        flows.add(new CashFlow(
                "F006",
                "2024-03-03",
                FlowType.OUTFLOW,
                Category.PAYABLE,
                400000,
                "Vendor A"
        ));


       
        flows.add(new CashFlow(
                "F007",
                "2024-03-04",
                FlowType.INFLOW,
                Category.RECEIVABLE,
                900000,
                "Client C"
        ));

        flows.add(new CashFlow(
                "F008",
                "2024-03-04",
                FlowType.OUTFLOW,
                Category.PAYABLE,
                600000,
                "Suppliers"
        ));


        flows.add(new CashFlow(
                "F009",
                "2024-03-05",
                FlowType.INFLOW,
                Category.RECEIVABLE,
                250000,
                "Small Client"
        ));

        flows.add(new CashFlow(
                "F010",
                "2024-03-05",
                FlowType.OUTFLOW,
                Category.PAYABLE,
                1100000,
                "Payroll Run"
        ));


     
        flows.add(new CashFlow(
                "F011",
                "2024-03-06",
                FlowType.INFLOW,
                Category.RECEIVABLE,
                300000,
                "Client D"
        ));

        flows.add(new CashFlow(
                "F012",
                "2024-03-06",
                FlowType.OUTFLOW,
                Category.LOAN_REPAYMENT,
                750000,
                "Bank Loan"
        ));


        flows.add(new CashFlow(
                "F013",
                "2024-03-07",
                FlowType.INFLOW,
                Category.RECEIVABLE,
                850000,
                "Client E"
        ));

        flows.add(new CashFlow(
                "F014",
                "2024-03-07",
                FlowType.OUTFLOW,
                Category.INVESTMENT,
                500000,
                "Treasury Investment"
        ));


       
        flows.add(new CashFlow(
                "F015",
                "2024-03-08",
                FlowType.INFLOW,
                Category.RECEIVABLE,
                1200000,
                "Client F"
        ));

        flows.add(new CashFlow(
                "F016",
                "2024-03-08",
                FlowType.OUTFLOW,
                Category.PAYABLE,
                350000,
                "Vendor B"
        ));


       
        flows.add(new CashFlow(
                "F017",
                "2024-03-09",
                FlowType.INFLOW,
                Category.LOAN_REPAYMENT,
                500000,
                "Loan Recovery"
        ));

        flows.add(new CashFlow(
                "F018",
                "2024-03-09",
                FlowType.OUTFLOW,
                Category.PAYABLE,
                450000,
                "Operations"
        ));


       
        flows.add(new CashFlow(
                "F019",
                "2024-03-10",
                FlowType.INFLOW,
                Category.RECEIVABLE,
                1000000,
                "Client G"
        ));

        flows.add(new CashFlow(
                "F020",
                "2024-03-10",
                FlowType.OUTFLOW,
                Category.INVESTMENT,
                650000,
                "Market Investment"
        ));


        return flows;
    }
}
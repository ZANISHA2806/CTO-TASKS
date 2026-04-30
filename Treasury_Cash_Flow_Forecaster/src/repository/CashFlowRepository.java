package repository;

import model.CashFlow;
import model.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CashFlowRepository {

    private TreeMap<String, List<CashFlow>> byDate = new TreeMap<>();

    private HashMap<Category, Double> categoryTotals = new HashMap<>();

    public void addFlow(CashFlow flow) {

        byDate.computeIfAbsent(
                flow.getDate(),
                k -> new ArrayList<>()
        ).add(flow);


        categoryTotals.put(
                flow.getCategory(),
                categoryTotals.getOrDefault(
                        flow.getCategory(),
                        0.0
                ) + flow.getAmount()
        );
    }

    public List<CashFlow> getFlowsByDate(String date) {

        return byDate.getOrDefault(
                date,
                new ArrayList<>()
        );
    }

    public TreeMap<String, List<CashFlow>> getAllFlows() {
        return byDate;
    }

    public Map<Category, Double> getCategoryTotals() {
        return categoryTotals;
    }


    public NavigableMap<String, List<CashFlow>> getFlowsInRange(
            String fromDate,
            String toDate) {

        return byDate.subMap(
                fromDate,
                true,
                toDate,
                true
        );
    }

}
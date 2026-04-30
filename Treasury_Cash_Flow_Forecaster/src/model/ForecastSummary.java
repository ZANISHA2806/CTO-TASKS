package model;
import java.util.Map;
public class ForecastSummary {
private double totalinflow;
private double totaloutflow;
private double totalnetflow;
private Map<Category,Double>categorytotal;


public ForecastSummary(double totalinflow,double totaloutflow,double totalnetflow,Map<Category,Double>categorytotal) {
	this.totalinflow=totalinflow;
	this.totaloutflow=totaloutflow;
	this.totalnetflow=totalnetflow;
	this.categorytotal=categorytotal;
}

public double getTotalinflow() {
	return totalinflow;
}
public double getTotaloutflow() {
	return totaloutflow;
}
public double getTotalnetflow() {
	return totalnetflow;
}

public Map<Category, Double> getCategoryTotal() {
    return categorytotal;
}
@Override
public String toString() {
	 StringBuilder summary = new StringBuilder();
	 summary.append("\nForecast Summary \n");
     summary.append("Total Inflows : ").append(totalinflow).append("\n");
     summary.append("Total Outflows: ").append(totaloutflow).append("\n");
     summary.append("Net Flow      : ").append(totalnetflow).append("\n");

     summary.append("\nCategory Breakdown:\n");

	 for(Map.Entry<Category, Double> entry : categorytotal.entrySet()) {
         summary.append(entry.getKey())
                .append(" : ")
                .append(entry.getValue())
                .append("\n");
     }

     return summary.toString();
	}
}


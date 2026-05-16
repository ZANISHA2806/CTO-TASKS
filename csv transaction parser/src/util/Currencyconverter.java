package util;

import java.util.*;

public class Currencyconverter {

    public static double convertToINR(double amount, String currency, Map<String, Double> rates) {

        currency = currency.toUpperCase();

        if (currency.equals("INR")) {
            return amount;
        }

        if (!rates.containsKey(currency)) {
            System.out.println("unknown currency : " + currency);
            return amount;
        }

        return amount * rates.get(currency); 
    }
}
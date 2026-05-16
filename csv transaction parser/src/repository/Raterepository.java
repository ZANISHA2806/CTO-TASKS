package repository;

import java.io.*;
import java.util.*;

public class Raterepository {

    public static Map<String, Double> loadrate(String filepath) throws IOException {

        Map<String, Double> rates = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("=");

                if (parts.length != 2) {
                    System.out.println("Invalid rate line: " + line);
                    continue;
                }

                String currency = parts[0].trim().toUpperCase();
                double rate = Double.parseDouble(parts[1].trim());

                rates.put(currency, rate);
            }
        }

        return rates;
    }
}
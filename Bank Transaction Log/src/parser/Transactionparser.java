package parser;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.Transaction;
import model.Transactiontype;

public class Transactionparser {

    public static Transaction parse(String line) {
        try {
            String[] parts = line.split("\\|");

            String tid = parts[0];
            String acc = parts[1];
            String type = parts[2];
            double amount = Double.parseDouble(parts[3]);
            String tdate = parts[4];

            Transactiontype transtype =
                Transactiontype.valueOf(type.trim().toUpperCase());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(tdate);

            return new Transaction(tid, acc, transtype, amount, date);

        } catch (Exception e) {
            System.out.println("Error parsing line: " + line);
            return null;
        }
    }
}
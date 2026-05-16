package util;

import model.Transaction;
import exception.ParserException;
import model.Transactiontype;

public class ParserUtil {

    public static Transaction parse(String line) throws ParserException {

        try {
            String[] parts = line.split(",");

            if (parts.length != 7) {
                throw new ParserException("Invalid column count: " + parts.length);
            }

            String tid = parts[0].trim();
            String acc = parts[1].trim();

            
            Transactiontype type;
            try {
                type = Transactiontype.valueOf(parts[2].trim().toUpperCase());
            } catch (Exception e) {
                throw new ParserException("Invalid transaction type: " + parts[2]);
            }

            double amount;
            try {
                amount = Double.parseDouble(parts[3].trim());
            } catch (NumberFormatException e) {
                throw new ParserException("Invalid amount: " + parts[3]);
            }

            String currency = parts[4].trim().toUpperCase();
            String valuedate = parts[5].trim();
            String status = parts[6].trim();

            return new Transaction(
                    tid,
                    acc,
                    type,
                    amount,
                    currency,
                    valuedate,
                    status
            );

        } catch (ParserException e) {
            throw e;
        } catch (Exception e) {
            throw new ParserException("Malformed row: " + line);
        }
    }
}
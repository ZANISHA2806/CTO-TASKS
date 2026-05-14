package generator;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

import model.Transaction;

public class TransactionGenerator {

    public List<Transaction> generateTransactions() {

        Random random = new Random();

        List<Transaction> transactions = new ArrayList<>();

        for (int i = 1; i <= 1000; i++) {

            String transactionId = String.format("TXN_%04d", i);

            double amount =
                    100 + (50000 - 100) * random.nextDouble();

            long timestamp =
                    System.currentTimeMillis() + i;

            byte typeCode =
                    (byte) (random.nextBoolean() ? 1 : 2);

            Transaction transaction =
                    new Transaction(
                            transactionId,
                            amount,
                            timestamp,
                            typeCode
                    );

            transactions.add(transaction);
        }

        return transactions;
    }
}
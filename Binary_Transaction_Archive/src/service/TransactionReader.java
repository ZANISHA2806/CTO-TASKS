package service;

import model.Transaction;
import util.LoggerUtil;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionReader {

    public List<Transaction> readTransactions(
            String filePath
    ) {

        List<Transaction> transactions =
                new ArrayList<>();

        long starttime =
                System.nanoTime();

        try (

                DataInputStream dis =
                        new DataInputStream(

                                new BufferedInputStream(

                                        new FileInputStream(
                                                filePath
                                        ),

                                        64 * 1024
                                )
                        )
        ) {

            while (true) {

                try {

                    String id =
                            dis.readUTF();

                    double amount =
                            dis.readDouble();

                    long timestamp =
                            dis.readLong();

                    byte typeCode =
                            dis.readByte();

                    Transaction transaction =
                            new Transaction(
                                    id,
                                    amount,
                                    timestamp,
                                    typeCode
                            );

                    transactions.add(
                            transaction
                    );

                } catch (EOFException e) {

                    LoggerUtil.info(
                            "End of file reached."
                    );

                    break;
                }
            }

            long endtime =
                    System.nanoTime();

            double elapsedms =
                    (endtime - starttime)
                            / 1_000_000.0;

            File file =
                    new File(filePath);

            LoggerUtil.info(
                    "Transactions read successfully."
            );

            LoggerUtil.info(
                    "File Name : "
                            + file.getName()
            );

            LoggerUtil.info(
                    "Total Transactions Read : "
                            + transactions.size()
            );

            LoggerUtil.info(
                    "Read Time : "
                            + elapsedms
                            + " ms"
            );

        } catch (IOException e) {

            LoggerUtil.error(
                    "Error reading transactions : "
                            + e.getMessage()
            );
        }

        return transactions;
    }
}
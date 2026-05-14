package service;
import java.io.BufferedWriter;
import java.util.List;
import model.Transaction;
import util.LoggerUtil;

import java.io.DataOutputStream;
import java.io.File;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class TransactionWriter {
	
	public double writeBuffered(List<Transaction>transactions,String filepath) {

        long startTime =
                System.nanoTime();

        try (

                DataOutputStream dos =
                        new DataOutputStream(

                                new BufferedOutputStream(

                                        new FileOutputStream(
                                                filepath
                                        ),

                                        64 * 1024
                                )
                        )
        ) {

            writeTransaction(
                    dos,
                    transactions
            );

            dos.flush();

        } catch (IOException e) {

            LoggerUtil.error(
                    "Buffered Write Error : "
                            + e.getMessage()
            );
        }

        long endTime =
                System.nanoTime();

        double elapsedMs =
                (endTime - startTime)
                        / 1_000_000.0;

        LoggerUtil.info(
                "Buffered Write Time : "
                        + elapsedMs
                        + " ms"
        );

        return elapsedMs;
    }


    public double writeUnbuffered(

            List<Transaction> transactions,

            String filePath
    ) {

        long startTime =
                System.nanoTime();

        try (

                DataOutputStream dos =
                        new DataOutputStream(

                                new FileOutputStream(
                                        filePath
                                )
                        ) 
        ) {

            writeTransaction(
                    dos,
                    transactions
            );

            dos.flush();

        } catch (IOException e) {

            LoggerUtil.error(
                    "Unbuffered Write Error : "
                            + e.getMessage()
            );
        }

        long endTime =
                System.nanoTime();

        double elapsedMs =
                (endTime - startTime)
                        / 1_000_000.0;

        LoggerUtil.info(
                "Unbuffered Write Time : "
                        + elapsedMs
                        + " ms"
        );

        return elapsedMs;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    private void writeTransaction(

            DataOutputStream dos,

            List<Transaction> transactions

    ) throws IOException {

        for (Transaction transaction : transactions) {

            dos.writeUTF(
                    transaction.getTransactionId()
            );

            dos.writeDouble(
                    transaction.getAmount()
            );

            dos.writeLong(
                    transaction.getTimestamp()
            );

            dos.writeByte(
                    transaction.getTypeCode()
            );
        }
    }
}

package main;

import util.LoggerUtil;
import generator.TransactionGenerator;
import model.Transaction;
import service.TransactionWriter;
import java.util.List;
import service.TransactionReader;
import service.Verification;
import java.io.File;
public class Main {
public static void main(String[] args) {
	LoggerUtil.info("Application started");
	
	TransactionGenerator generator=new TransactionGenerator();
	List<Transaction>transactions=generator.generateTransactions();
	LoggerUtil.info("1000 transactions generated");
	TransactionWriter writer=new TransactionWriter();
	writer.writeBuffered(
	        transactions,
	        "src/data/archive_buffered.bin"
	);
	  double unbufferedWriteTime =
              writer.writeUnbuffered(
                      transactions,
                      "src/data/archive_unbuffered.bin"
              );

	//LoggerUtil.info("Transaction written successfully");
	TransactionReader reader=new TransactionReader();
	List<Transaction>readTransaction=reader.readTransactions("src/data/archive_buffered.bin");
	LoggerUtil.info(
            "Transactions Reconstructed : "
                    + readTransaction.size()
    );
	Verification verification=new Verification();
	verification.verifytransaction(transactions, readTransaction);
	LoggerUtil.info(
            "transaction verified successfully"
    );
	
	 File bufferedFile =
             new File(
                     "src/data/archive_buffered.bin"
             );

     File unbufferedFile =
             new File(
                     "src/data/archive_unbuffered.bin"
             );


     LoggerUtil.info(
             "Buffered File Size : "
                     + bufferedFile.length()
                     + " bytes"
     );

     LoggerUtil.info(
             "Unbuffered File Size : "
                     + unbufferedFile.length()
                     + " bytes"
     );

	
}
}

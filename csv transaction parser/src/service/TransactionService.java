package service;

import util.CSVReader;
import java.util.*;
import model.Transaction;
import util.*;
import java.io.*;
import exception.ParserException;
public class TransactionService {
public void process(String input,String output,Map<String,Double> rate)throws Exception{
	List<String>lines=CSVReader.readLines(input);
	Set<String>currencies=new HashSet<>();
	List<Transaction> validTransactions = new ArrayList<>();
	int filteredcount=0;
	int processed=0;
	
	for(String line:lines) {
		if(line.trim().toLowerCase().startsWith("tid")) {
			continue;
		}
		processed++;
		try {
			Transaction t=ParserUtil.parse(line);
			currencies.add(t.getCurrency());
			
			if(t.getStatus().equalsIgnoreCase("FAILED")){
				
				filteredcount++;
				continue;
			}
			double inr=Currencyconverter.convertToINR(
					t.getAmount(),
					t.getCurrency(),
					rate);
			
		    t.setInrAmount(inr);

            validTransactions.add(t);

        } catch (ParserException e) {
            System.out.println("Skipping row: " + e.getMessage());
        }
    }

    writeOutput(output, validTransactions);

    System.out.println("Summary:");
    System.out.println("Rows processed : " + processed);
    System.out.println("Rows filtered  : " + filteredcount);
    System.out.println("Currencies seen: " + currencies);
}

private void writeOutput(String file, List<Transaction> list) throws IOException {

    try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {

       pw.println("txnId,accountId,transactionType,amount,currency,valueDate,status,inrAmount");

        for (Transaction t : list) {
            pw.println(
                    t.getTid() + "," +
                    t.getAcc() + "," +
                    t.getType() + "," +
                    t.getAmount() + "," +
                    t.getCurrency() + "," +
                    t.getValuedate() + "," +
                    t.getStatus() + "," +
                    t.getInrAmount()
            );
        
		}
	}
}
}

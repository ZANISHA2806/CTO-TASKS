package main;
import java.util.List;
import sample.Data;
import model.ShareholderRecord;
import service.DividendEngine;
import exception.*;
import util.DividendLogger;
public class Main {
public static void main(String[] args) {
	 List<ShareholderRecord> shareholders =
             Data.getSampleShareholders();
	   DividendEngine engine = new DividendEngine(
               shareholders,
               5.0,
               0.10
       );
	   try {
		   engine.processDistribution();
	   }catch(DividendProcessingHaltException e) {
		   DividendLogger.logHalt(e);
	   }
	   engine.generateDividendStatement();
}
}

package service;

import java.util.List;

import model.Transaction;
import util.LoggerUtil;
public class Verification {
public void verifytransaction(List<Transaction>writtentransaction,List<Transaction>readtransaction) {
	verifycount(writtentransaction,readtransaction);
	verifytotalamount(writtentransaction,readtransaction);
}
private void verifycount(List<Transaction>writtentransaction,List<Transaction>readtransaction) {
	int writtencount=writtentransaction.size();
	int readcount=readtransaction.size();
	if(writtencount==readcount) {
		LoggerUtil.info("count verified: "+readcount);
	}else {
		LoggerUtil.error("count verification failed");
		 LoggerUtil.error(
                 "Written Count : "+ writtencount
         );

         LoggerUtil.error(
                 "Read Count : "+ readcount
         );
	}
	
}
private void verifytotalamount(List<Transaction>writtentransaction,List<Transaction>readtransaction) {
	double totalwritten=calculatetotal(writtentransaction);
	double totalread=calculatetotal(readtransaction);
	double difference=Math.abs(totalwritten-totalread);
	
	if(difference<0.001) {
		LoggerUtil.info(
                "Amount Verification PASSED"
        );

        LoggerUtil.info(
                "Total Written Amount : "
                        + totalwritten
        );

        LoggerUtil.info(
                "Total Read Amount : "
                        + totalread
        );
	}else {
		LoggerUtil.info(
                "Amount Verification FAILED"
        );

        LoggerUtil.info(
                "Total Written Amount : "
                        + totalwritten
        );

        LoggerUtil.info(
                "Total Read Amount : "
                        + totalread
        );
	}
}

private double calculatetotal(List<Transaction>transactions) {
	double total=0;
	for (Transaction transaction
            : transactions) {

        total += transaction.getAmount();
    }

    return total;
}
}

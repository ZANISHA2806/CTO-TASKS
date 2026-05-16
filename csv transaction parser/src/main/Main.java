package main;
import service.TransactionService;
import repository.Raterepository;
import java.util.Map;
public class Main {
public static void main(String[] args) {
	try {
		String input="src/sample/input.txt";
		String output="src/sample/output.txt";
		String rate="src/sample/rate.txt";
		Map<String,Double>rates=Raterepository.loadrate(rate);
		TransactionService service=new TransactionService();
		service.process(input, output, rates);
		System.out.println("process completed successfully");
		
		
	}
	catch(Exception e) {
		System.out.println("something went wrong");
		e.printStackTrace();
	}
}
}

package main;
import exception.CardSuspendedException;
import exception.LoadLimitException;
import exception.SourceAccountException;
import service.CardService;
import util.LoggerUtil;
import sample.SampleData;
import model.LoadRequest;
import exception.PartialLoadException;

public class Main {
public static void main(String[] args) {
	CardService service=new CardService();
	for(LoadRequest request:SampleData.getRequests()) {
		try {
			service.loadcard(request.getCardId(),request.getsourceVPA(),request.getamount());
		}catch(CardSuspendedException e) {
			LoggerUtil.error(e.getMessage());
			System.out.println("FAILED - "+e.getMessage());
			
		}catch(LoadLimitException e) {
			LoggerUtil.error(e.getMessage());
			System.out.println(
				    request.getCardId() + ": FAILED — " +
				    e.getClass().getSimpleName() + ": " +
				    e.getMessage()
				);
			
		}
		catch(SourceAccountException e) {
			LoggerUtil.error(e.getMessage());
			System.out.println("FAILED - "+e.getMessage());
			
		}
		catch (PartialLoadException e) {
		    System.out.println(
		        request.getCardId() + ": PARTIAL — Rs." +
		        e.getLoadedamount() + " loaded; Rs." +
		        e.getFailedamount() + " failed (source insufficient)"
		    );

		    System.out.println(
		        "  Retry remainder of Rs." + e.getFailedamount() + "? (Y/N)"
		    );
		}
	}
		
}
}

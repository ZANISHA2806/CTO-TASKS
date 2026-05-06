package service;
import model.Card;
import model.Account;
import exception.CardSuspendedException;
import repository.CardRepository;
import repository.AccountRepository;
import util.LoggerUtil;
import exception.LoadLimitException;
import enums.LimitType;
import enums.AccountStatus;
import exception.SourceAccountException;
import exception.PartialLoadException;
public class CardService {
CardRepository cardrepo=new CardRepository();
AccountRepository accrepo=new AccountRepository();

public void loadcard(String cardId, String sourceVPA, double amount)
        throws CardSuspendedException, LoadLimitException, SourceAccountException ,PartialLoadException{

    Card card = cardrepo.getById(cardId);
    
    if (card == null) {
        LoggerUtil.error("Card not found: " + cardId);
        System.out.println("Card not found");
        return;
    }

    if (card.isSuspended()) {
        LoggerUtil.error("card suspension: " + cardId);
        throw new CardSuspendedException(cardId, "reported lost");
    }

    if (amount > LimitType.SINGLELIMIT.getLimit()) {
        throw new LoadLimitException(amount, LimitType.SINGLELIMIT);
    }

    if (card.getDailyTotal() + amount > LimitType.DAILYLIMIT.getLimit()) {
        throw new LoadLimitException(amount, LimitType.DAILYLIMIT);
    }
   
    
    AccountStatus status=accrepo.getStatus(sourceVPA);
    if(status==AccountStatus.FROZEN) {
    	throw new SourceAccountException(sourceVPA,AccountStatus.FROZEN);
    }
    if(status==AccountStatus.INVALID) {
    	throw new SourceAccountException(sourceVPA,AccountStatus.INVALID);
    }
    
    	
    double balance = accrepo.getBalance(sourceVPA);

    if (balance < amount) {
        accrepo.debit(sourceVPA, balance);
        card.addToDailyTotal(balance);

        throw new PartialLoadException(balance, amount - balance);
    }

    accrepo.debit(sourceVPA, amount);
    card.addToDailyTotal(amount);

    LoggerUtil.info("card loaded: " + cardId);
    System.out.println(
    	    cardId + ": SUCCESS — Rs." + amount +
    	    " loaded to prepaid card " + cardId
    	);
    }


}

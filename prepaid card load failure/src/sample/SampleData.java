package sample;

import enums.AccountStatus;
import model.Card;
import model.LoadRequest;

import java.util.*;

public final class SampleData {

    private SampleData() {}

    private static final Map<String, Card> CARDS = new HashMap<>();
    private static final Map<String, Double> BALANCES = new HashMap<>();
    private static final Map<String, AccountStatus> ACCOUNT_STATUS = new HashMap<>();
    private static final List<LoadRequest> REQUESTS = new ArrayList<>();

    static {
        CARDS.put("C001", new Card("C001", false));
        CARDS.put("C002", new Card("C002", false));
        CARDS.put("C003", new Card("C003", true));
        CARDS.put("C004", new Card("C004", false));
        CARDS.put("C005", new Card("C005", false));

        BALANCES.put("alice@upi", 20000.0);
        BALANCES.put("bob@upi", 10000.0);
        BALANCES.put("carol@upi", 4000.0);
        BALANCES.put("dave@upi", 5000.0);

        ACCOUNT_STATUS.put("alice@upi", AccountStatus.VALID);
        ACCOUNT_STATUS.put("bob@upi", AccountStatus.FROZEN);
        ACCOUNT_STATUS.put("carol@upi", AccountStatus.VALID);
        ACCOUNT_STATUS.put("dave@upi", AccountStatus.VALID);
        ACCOUNT_STATUS.put("invalid@upi", AccountStatus.INVALID);

        REQUESTS.add(new LoadRequest("C001", "alice@upi", 15000));
        REQUESTS.add(new LoadRequest("C002", "bob@upi", 5000));
        REQUESTS.add(new LoadRequest("C003", "alice@upi", 8000));
        REQUESTS.add(new LoadRequest("C004", "carol@upi", 6000));
        REQUESTS.add(new LoadRequest("C005", "dave@upi", 3000));
        REQUESTS.add(new LoadRequest("C005", "dave@upi", 25000));
        REQUESTS.add(new LoadRequest("C001", "invalid@upi", 2000));
    }

    public static Card getCard(String cardId) {
        return CARDS.get(cardId);
    }

    public static boolean isCardPresent(String cardId) {
        return CARDS.containsKey(cardId);
    }

    public static boolean isAccountPresent(String vpa) {
        return ACCOUNT_STATUS.containsKey(vpa);
    }

    public static double getBalance(String vpa) {
        return BALANCES.getOrDefault(vpa, 0.0);
    }

    public static void updateBalance(String vpa, double newBalance) {
        BALANCES.put(vpa, newBalance);
    }

    public static AccountStatus getAccountStatus(String vpa) {
        return ACCOUNT_STATUS.getOrDefault(vpa, AccountStatus.INVALID);
    }

    public static List<LoadRequest> getRequests() {
        return Collections.unmodifiableList(REQUESTS);
    }
}
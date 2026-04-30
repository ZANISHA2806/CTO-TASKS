package util;

import enums.PaymentEvent;

public class MessageBuilder {

    public static String buildmessage(
            PaymentEvent event,
            double amount) {

        switch(event) {

            case SUCCESS:
                return "Payment success Rs."
                        + amount;

            case FAILURE:
                return "Payment failed Rs."
                        + amount;

            case REFUND:
                return "Refund processed Rs."
                        + amount;

            case DISPUTE_RAISED:
                return "Dispute raised for Rs."
                        + amount;

            case DISPUTE_RESOLVED:
                return "Dispute resolved for Rs."
                        + amount;

            default:
                return "Payment update";
        }
    }
}
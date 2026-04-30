package service;

import java.util.*;
import enums.PaymentEvent;

public class NotificationRouter {

    private Map<PaymentEvent,List<String>>
        routingRules = new HashMap<>();


    public void configureRoute(
            PaymentEvent event,
            List<String> channels) {

        routingRules.put(event, channels);
    }


    public List<String> getChannelsForEvent(
            PaymentEvent event) {

        return routingRules.getOrDefault(
            event,
            new ArrayList<>()
        );
    }
}
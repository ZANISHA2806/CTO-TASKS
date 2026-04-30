package config;

import java.util.Arrays;

import enums.PaymentEvent;
import service.NotificationRouter;

public class RouterConfig {

    private RouterConfig(){}

    public static void configureRoutes(
            NotificationRouter router) {

        router.configureRoute(
            PaymentEvent.SUCCESS,
            Arrays.asList(
                "SMS",
                "EMAIL",
                "PUSH"
            )
        );


        router.configureRoute(
            PaymentEvent.FAILURE,
            Arrays.asList(
                "SMS",
                "EMAIL"
            )
        );


        router.configureRoute(
            PaymentEvent.REFUND,
            Arrays.asList(
                "EMAIL",
                "PUSH"
            )
        );


        router.configureRoute(
            PaymentEvent.DISPUTE_RAISED,
            Arrays.asList(
                "EMAIL",
                "PUSH"
            )
        );


        router.configureRoute(
            PaymentEvent.DISPUTE_RESOLVED,
            Arrays.asList(
                "SMS",
                "EMAIL"
            )
        );
    }
}
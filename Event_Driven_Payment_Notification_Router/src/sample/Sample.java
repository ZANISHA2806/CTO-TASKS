package sample;

import enums.PaymentEvent;
import service.NotificationService;

public class Sample {

    


    public static void loadsample(
            NotificationService service) {

        service.enqueueNotifications(
                "U001",
                PaymentEvent.SUCCESS,
                5000
        );

        service.enqueueNotifications(
                "U001",
                PaymentEvent.FAILURE,
                1200
        );

        service.enqueueNotifications(
                "U002",
                PaymentEvent.REFUND,
                900
        );

        service.enqueueNotifications(
                "U003",
                PaymentEvent.DISPUTE_RAISED,
                7000
        );

        service.enqueueNotifications(
                "U002",
                PaymentEvent.DISPUTE_RESOLVED,
                3000
        );

        service.enqueueNotifications(
                "U001",
                PaymentEvent.SUCCESS,
                2500
        );

        service.enqueueNotifications(
                "U003",
                PaymentEvent.FAILURE,
                1500
        );

        service.enqueueNotifications(
                "U002",
                PaymentEvent.REFUND,
                600
        );

        service.enqueueNotifications(
                "U003",
                PaymentEvent.SUCCESS,
                8000
        );

        service.enqueueNotifications(
                "U001",
                PaymentEvent.DISPUTE_RESOLVED,
                1000
        );
    }
}
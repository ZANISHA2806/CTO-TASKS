package service;
import java.util.*;
import java.util.logging.Logger;
import enums.PaymentEvent;
import model.Notification;
import util.IdGenerator;
import util.MessageBuilder;
import util.LoggerUtil;
public class NotificationService {

	private static final Logger log = LoggerUtil.getLogger();
	private Map<String,List<Notification>> sentbyuser;
	
	private Queue<Notification> pendingqueue;
	private NotificationRouter router;
	
	public NotificationService(NotificationRouter router) {
		 this.router = router;
	        this.sentbyuser = new HashMap<>();
	        this.pendingqueue = new LinkedList<>();
	}
	   public void enqueueNotifications(
	            String userId,
	            PaymentEvent event,
	            double amount) {

	        List<String> channels =
	                router.getChannelsForEvent(event);

	        if (channels == null || channels.isEmpty()) {
	            log.warning(
	                "No channels configured for event: "
	                + event
	            );
	            return;
	        }

	        for (String channel : channels) {

	            Notification notification =
	                    new Notification(
	                        IdGenerator.generateId(),
	                        userId,
	                        channel,
	                        event,
	                        MessageBuilder.buildmessage(
	                            event,
	                            amount
	                        ),
	                        System.currentTimeMillis()
	                    );

	            pendingqueue.offer(notification);
	        }
	    }
	   public void dispatch() {

	        while (!pendingqueue.isEmpty()) {

	            Notification n = pendingqueue.poll();

	            sentbyuser
	                .computeIfAbsent(
	                    n.getUserId(),
	                    k -> new ArrayList<>()
	                )
	                .add(n);

	            log.info(
	               "Dispatched: "
	                + n.toString()
	            );
	        }
	    }
	   public Map<String,Integer> getSentSummary(
	            String userId) {

	        Map<String,Integer> summary =
	                new HashMap<>();

	        List<Notification> list =
	                sentbyuser.getOrDefault(
	                    userId,
	                    new ArrayList<>()
	                );

	        for(Notification n : list){

	            summary.put(
	                n.getChannel(),
	                summary.getOrDefault(
	                    n.getChannel(),
	                    0
	                ) + 1
	            );
	        }

	        return summary;
	    }
	   
	   public PaymentEvent getMostActiveEvent() {

	        Map<PaymentEvent,Integer> counts =
	                new HashMap<>();

	        for(List<Notification> userList :
	                sentbyuser.values()) {

	            for(Notification n : userList) {

	                counts.put(
	                    n.getEventType(),
	                    counts.getOrDefault(
	                        n.getEventType(),
	                        0
	                    ) + 1
	                );
	            }
	        }

	        PaymentEvent mostActive = null;
	        int max = 0;

	        for(Map.Entry<PaymentEvent,Integer> entry :
	                counts.entrySet()) {

	            if(entry.getValue() > max) {
	                max = entry.getValue();
	                mostActive = entry.getKey();
	            }
	        }

	        return mostActive;
	    }


}

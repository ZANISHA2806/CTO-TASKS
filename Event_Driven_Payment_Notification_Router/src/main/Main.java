package main;

import config.RouterConfig;
import enums.PaymentEvent;
import service.NotificationRouter;
import service.NotificationService;
import util.LoggerUtil;
import sample.Sample;
import java.util.logging.Logger;
public class Main {

	private static final Logger log=LoggerUtil.getLogger();
	public static void main(String[] args) {
		
		NotificationRouter router=new NotificationRouter();
		
		RouterConfig.configureRoutes(router);
		
		NotificationService service=new NotificationService(router);
		
		Sample.loadsample(service);
		service.dispatch();
		
		 log.info(
		           "Summary U001: "
		           + service.getSentSummary(
		               "U001")
		        );

		        log.info(
		           "Summary U002: "
		           + service.getSentSummary(
		               "U002")
		        );

		        log.info(
		           "Summary U003: "
		           + service.getSentSummary(
		               "U003")
		        );
		
		log.info(
		          "Most active event: "
		          + service.getMostActiveEvent()
		        );
		
		
	}
}

package model;

import enums.PaymentEvent;

public class Notification {
	
	private final String notificationId;
	private final String userId;
	private final String channel;
	private final PaymentEvent eventType;
	private final String message;
	private final long sentAt;
	
	public Notification(String notificationId,String userId,String channel,PaymentEvent eventType,String message,long sentAt) {
		this.notificationId=notificationId;
		this.userId=userId;
		this.channel=channel;
		this.eventType=eventType;
		this.message=message;
		this.sentAt=sentAt;
	}
		
	
		public String getNotificationId() {
			return notificationId;
		}
		public String getUserId() {
			return userId;
		}
		public String getChannel() {
			return channel;
		}
		public PaymentEvent getEventType() {
			return eventType;
		}
		public String getMessage() {
			return message;
		}
		public long getSentAt() {
			return sentAt;
		}
		
		@Override
		public String toString() {
			return userId+" "+channel+" "+eventType+" "+message; 
		}
	
}

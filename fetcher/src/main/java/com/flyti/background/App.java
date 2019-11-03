package com.flyti.background;

import com.flyti.background.Message.MessagePublisher;
import com.flyti.background.Message.MessagePublisherFactory;

public class App  {
	private static MessagePublisher messagePublisher;
	
	public static MessagePublisher getMessagePublisher() {
		return messagePublisher;
	}
	
	private static void setMessagePublisher(MessagePublisher messagePublisher) {
		App.messagePublisher = messagePublisher;
	}
	
    public static void main(String[] args)
    {
    	App.setMessagePublisher(MessagePublisherFactory.Create(new Configuration()));
    	App.messagePublisher.run();
    }
}

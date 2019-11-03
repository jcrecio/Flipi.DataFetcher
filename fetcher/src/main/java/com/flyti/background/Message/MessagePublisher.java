package com.flyti.background.Message;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import com.flyti.background.Configuration;

public class MessagePublisher {
	private KafkaProducer kafkaProducer;

    public MessagePublisher(KafkaProducer<?, ?> kafkaProducer) {
    	this.kafkaProducer = kafkaProducer;
    }
    
    public void run() {
    	Configuration configuration = new Configuration();
        for (int index = 0; index < configuration.MESSAGE_COUNT; index++) {
        	Message message = new Message();
        	message.setFlightReference("12345");
            ProducerRecord<String, Message> record = new ProducerRecord<String, Message>(configuration.TOPIC_NAME, message);

            try {
                RecordMetadata metadata = (RecordMetadata) kafkaProducer.send(record).get();
                System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
                        + " with offset " + metadata.offset());
                 } 
            catch (ExecutionException e) {
                     System.out.println("Error in sending record");
                     System.out.println(e);
                  } 
             catch (InterruptedException e) {
                      System.out.println("Error in sending record");
                      System.out.println(e);
                  }
         }
    }
}
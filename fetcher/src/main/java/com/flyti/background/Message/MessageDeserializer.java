package com.flyti.background.Message;

import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageDeserializer implements Deserializer<Message> {
    public void configure(Map<String, ?> configs, boolean isKey) {
    }
    
    public Message deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        Message object = null;
        try {
        	object = mapper.readValue(data, Message.class);
        } catch (Exception exception) {
        	System.out.println("Error in deserializing bytes "+ exception);
        }
        return object;
    }
    
    public void close() {
    }
}
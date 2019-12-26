package com.flyti.background.Message;

import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageSerializer implements Serializer<Message> {
    public void configure(Map<String, ?> configs, boolean isKey) {
    }
    
    public byte[] serialize(String topic, Message data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        	retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception exception) {
        	System.out.println("Error in serializing object"+ data);
        }
        return retVal;
    }
    public void close() {
    }
    
}

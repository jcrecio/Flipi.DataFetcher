package com.flyti.background.Message;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import com.flyti.background.Configuration;

public class MessagePublisherFactory {
    public static MessagePublisher Create(Configuration configuration) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, configuration.KAFKA_BROKERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, configuration.CLIENT_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, MessageSerializer.class.getName());
        //props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, TopicPartitioner.class.getName());
        return new MessagePublisher(new KafkaProducer<>(props));
    }
}
package com.flyti.background.Message;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import com.flyti.background.MessageBusConfiguration;

public class MessagePublisherFactory {
    public static MessagePublisher Create(MessageBusConfiguration configuration) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, configuration.KAFKA_BROKERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, configuration.CLIENT_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, MessageSerializer.class.getName());
        //props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, TopicPartitioner.class.getName());
        MessagePublisher messagePublisher = new MessagePublisher(new KafkaProducer<Object, Object>(props));

        return messagePublisher;
    }
}
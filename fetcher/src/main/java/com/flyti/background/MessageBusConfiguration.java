package com.flyti.background;

public class MessageBusConfiguration {
    public String KAFKA_BROKERS = "bus_kafka:9093";
    public Integer MESSAGE_COUNT=1000;
    public String CLIENT_ID="fetcherpublisher";
    public String TOPIC_NAME="fetchertopic";
    public String GROUP_ID_CONFIG="fetcherconsumergroup";
    public Integer MAX_NO_MESSAGE_FOUND_COUNT=100;
    public String OFFSET_RESET_LATEST="latest";
    public String OFFSET_RESET_EARLIER="earliest";
    public Integer MAX_POLL_RECORDS=1;
}
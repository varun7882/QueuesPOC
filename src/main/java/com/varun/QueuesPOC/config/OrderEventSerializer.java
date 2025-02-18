package com.varun.QueuesPOC.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.varun.QueuesPOC.model.OrderEvent;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class OrderEventSerializer implements Serializer<OrderEvent> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, OrderEvent data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing OrderEvent", e);
        }
    }
}
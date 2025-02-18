package com.varun.QueuesPOC.service;


import com.varun.QueuesPOC.model.OrderEvent;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
    private static final String TOPIC = "my-topic";
    private static final String TOPIC2 = "my-topic2";

    @Autowired
    @Qualifier("orderProducer")
    private KafkaTemplate<String, OrderEvent> orderEventKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        logger.info("Producing message: {}", message);
        kafkaTemplate.send(TOPIC, message);
    }

    public void sendMessageWithKey(String key, String message) {
        logger.info("Producing message: {} with key: {}", message, key);
        kafkaTemplate.send(TOPIC, key, message);
    }

    public void sendOrder(OrderEvent orderEvent) {
        logger.info("Producing message: {} ", orderEvent);
        orderEventKafkaTemplate.send(TOPIC2, orderEvent.getCustomerId(), orderEvent);

    }
}
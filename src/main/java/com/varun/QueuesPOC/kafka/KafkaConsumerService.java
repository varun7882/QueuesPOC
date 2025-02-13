package com.varun.QueuesPOC.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "my-topic", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        log.info("Received message: {}", message);
        // Add your message processing logic here
    }

    // If you want to get message metadata
    /*
    @KafkaListener(topics = "my-topic", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeWithMetadata(
            ConsumerRecord<String, String> record) {
        logger.info("Received message: key={}, value={}, partition={}, offset={}",
            record.key(), record.value(), record.partition(), record.offset());
    }
    */
}
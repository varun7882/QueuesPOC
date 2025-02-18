package com.varun.QueuesPOC.kafka;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Some commands to start consumer
 * ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my-topic2 --partition 0 --from-beginning
 * ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my-topic2 --partition 1 --from-beginning
 * ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my-topic2 --partition 2 --from-beginning
 * ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic my-topic --from-beginning
 */
@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "my-topic2", groupId = "${spring.kafka.consumer.group-id2}")
    public void consumeOrderWithMetadata(
            ConsumerRecord<String, String> record) {
        log.info("Received order: key={}, value={}, partition={}, offset={}",
                record.key(), record.value(), record.partition(), record.offset());
    }

    // If you want to get message metadata

    @KafkaListener(topics = "my-topic", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeWithMetadata(
            ConsumerRecord<String, String> record) {
        log.info("Received message: key={}, value={}, partition={}, offset={}",
            record.key(), record.value(), record.partition(), record.offset());
    }

}
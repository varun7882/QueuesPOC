package com.varun.QueuesPOC.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

@Configuration
public class KafkaPartitionConfig implements Partitioner {

    private Random random;
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        int numPartitions = cluster.partitionCountForTopic(topic);
        if (keyBytes == null) {
            return random.nextInt(numPartitions);
        }
        // Hash the key to determine partition
        return Math.abs(Arrays.hashCode(keyBytes) % numPartitions);
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}

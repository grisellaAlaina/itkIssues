package ru.practice.alcour.orders.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.name}")
    private String topicName;

    @Value("${kafka.topic.partitions}")
    private int numPartitions;

    @Value("${kafka.topic.replication-factor}")
    private short replicationFactor;

    @Bean
    public NewTopic webLogsTopic() {
        return TopicBuilder.name(topicName)
                .partitions(numPartitions)
                .replicas(replicationFactor)
                .build();
    }
}

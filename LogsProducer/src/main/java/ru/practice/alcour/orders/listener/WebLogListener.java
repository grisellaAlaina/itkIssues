package ru.practice.alcour.orders.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class WebLogListener {

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Получено сообщение: " + record.value() +
                " из партиции: " + record.partition() +
                " с ключом: " + record.key());
    }
}

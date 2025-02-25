package ru.practice.alcour.shipping.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.practice.alcour.shipping.model.SentOrder;

import java.util.UUID;

@Service
public class ShippingService {

    private final KafkaTemplate<String, SentOrder> kafkaTemplate;
    private final String sentOrdersTopic;

    public ShippingService(KafkaTemplate<String, SentOrder> kafkaTemplate,
                           @Value("${kafka.topics.sent-orders}") String sentOrdersTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.sentOrdersTopic = sentOrdersTopic;
    }

    public SentOrder processShipping(String orderId) {
        String shipmentId = UUID.randomUUID().toString();
        String trackingNumber = "TRACK" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        SentOrder sentOrder = new SentOrder(orderId, "SENT", shipmentId, trackingNumber);

        kafkaTemplate.send(sentOrdersTopic, sentOrder);

        return sentOrder;
    }
}
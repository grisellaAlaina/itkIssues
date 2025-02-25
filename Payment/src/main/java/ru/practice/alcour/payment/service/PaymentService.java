package ru.practice.alcour.payment.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.practice.alcour.payment.model.Payment;

@Service
public class PaymentService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String payedOrdersTopic;

    public PaymentService(KafkaTemplate<String, Object> kafkaTemplate,
                          @Value("${kafka.topics.payed-orders}") String payedOrdersTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.payedOrdersTopic = payedOrdersTopic;
    }

    public Payment processPayment(String orderId, double amount) {
        // Логика обработки оплаты
        // Например, обращение к платежному шлюзу

        // Предположим, оплата прошла успешно
        Payment payment = new Payment(orderId, "PAYED", amount);

        // Отправка сообщения в топик payed_orders
        kafkaTemplate.send(payedOrdersTopic, payment);

        return payment;
    }
}
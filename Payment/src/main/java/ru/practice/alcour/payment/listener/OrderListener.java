package ru.practice.alcour.payment.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.practice.alcour.payment.model.OrderDTO;
import ru.practice.alcour.payment.service.PaymentService;

@Component
public class OrderListener {

    private final PaymentService paymentService;

    public OrderListener(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = "${kafka.topics.new-orders}", groupId = "payment-group")
    public void listenNewOrders(OrderDTO order) {
        String orderId = order.getOrderId();
        double amount = 100.0;

        paymentService.processPayment(orderId, amount);
    }
}
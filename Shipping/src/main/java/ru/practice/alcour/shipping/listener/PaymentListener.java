package ru.practice.alcour.shipping.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.practice.alcour.shipping.model.Payment;
import ru.practice.alcour.shipping.model.SentOrder;
import ru.practice.alcour.shipping.service.ShippingService;

@Component
public class PaymentListener {

    private final ShippingService shippingService;
    private static final Logger logger = LoggerFactory.getLogger(PaymentListener.class);

    public PaymentListener(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @KafkaListener(topics = "${kafka.topics.payed-orders}", groupId = "shipping-group")
    public void listenPayedOrders(Payment payment) {
        logger.info("Received payment for orderId: {}", payment.getOrderId());

        SentOrder sentOrder = shippingService.processShipping(payment.getOrderId());

        logger.info("Processed shipping for orderId: {}, shipmentId: {}, trackingNumber: {}",
                sentOrder.getOrderId(),
                sentOrder.getShipmentId(),
                sentOrder.getTrackingNumber());
    }
}
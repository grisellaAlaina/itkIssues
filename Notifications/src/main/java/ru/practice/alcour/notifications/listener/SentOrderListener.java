package ru.practice.alcour.notifications.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.practice.alcour.notifications.model.SentOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class SentOrderListener {

    private static final Logger logger = LoggerFactory.getLogger(SentOrderListener.class);

    @KafkaListener(topics = "${kafka.topics.sent-orders}", groupId = "notifications-group")
    public void listenSentOrders(SentOrder sentOrder) {
        logger.info("Уведомление: Ваш заказ с ID {} был успешно доставлен. Трекинг-номер: {}",
                sentOrder.getOrderId(), sentOrder.getTrackingNumber());
    }
}
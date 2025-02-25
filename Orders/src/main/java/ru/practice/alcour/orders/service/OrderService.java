package ru.practice.alcour.orders.service;

import org.springframework.stereotype.Service;
import ru.practice.alcour.orders.model.Order;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Service
public class OrderService {

    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    public Order createOrder(Order order) {
        orders.put(order.getOrderId(), order);
        return order;
    }

    public Order updateOrderStatus(String orderId, String status) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setStatus(status);
            orders.put(orderId, order);
        }
        return order;
    }

    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }
}

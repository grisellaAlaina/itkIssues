package ru.practice.alcour.orders.controller;

import ru.practice.alcour.orders.model.Order;
import ru.practice.alcour.orders.service.OrderService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topics.new-orders}")
    private String newOrdersTopic;

    public OrderController(OrderService orderService, KafkaTemplate<String, Object> kafkaTemplate) {
        this.orderService = orderService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Map<String, String> orderRequest) {
        String orderId = UUID.randomUUID().toString();
        String name = orderRequest.get("name");
        Order order = new Order(orderId, name, "NEW");
        orderService.createOrder(order);

        kafkaTemplate.send(newOrdersTopic, order);

        return ResponseEntity.status(201).body(order);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable String orderId,
            @RequestBody Map<String, String> updateRequest) {
        String status = updateRequest.get("status");
        Order updatedOrder = orderService.updateOrderStatus(orderId, status);

        if (updatedOrder != null) {
            kafkaTemplate.send(newOrdersTopic, updatedOrder);
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.status(404).body(Map.of("error", "Order not found"));
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable String orderId) {
        Order order = orderService.getOrder(orderId);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.status(404).body(Map.of("error", "Order not found"));
        }
    }
}
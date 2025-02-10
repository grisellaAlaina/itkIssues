package org.example.itkissues.controller;

import jakarta.validation.Valid;
import org.example.itkissues.model.Order;
import org.example.itkissues.model.Product;
import org.example.itkissues.repository.OrderRepository;
import org.example.itkissues.repository.ProductRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository,
                           ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        // Проверка наличия товаров на складе
        for (Product product : order.getProducts()) {
            Product dbProduct = productRepository.findById(product.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            if (dbProduct.getQuantityInStock() < 1) {
                throw new RuntimeException("Product out of stock: " + dbProduct.getName());
            }
        }
        order.setOrderDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
package ru.practice.alcour.payment.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practice.alcour.payment.model.Payment;
import ru.practice.alcour.payment.service.PaymentService;

import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> makePayment(@RequestBody Map<String, Object> paymentRequest) {
        String orderId = (String) paymentRequest.get("orderId");
        double amount = Double.parseDouble(paymentRequest.get("amount").toString());

        Payment payment = paymentService.processPayment(orderId, amount);

        return ResponseEntity.ok(payment);
    }
}

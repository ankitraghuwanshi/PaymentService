package org.example.paymentservice.controllers;

import com.stripe.exception.StripeException;
import org.example.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initiate/{orderId}")  //post because it generates link
    public String initiatePayment(@PathVariable Long orderId) throws StripeException {
        return paymentService.initiatePayment(orderId);
    }
}
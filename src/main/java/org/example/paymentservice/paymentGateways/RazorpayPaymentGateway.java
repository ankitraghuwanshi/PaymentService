package org.example.paymentservice.paymentGateways;

import org.springframework.stereotype.Component;

@Component
public class RazorpayPaymentGateway implements PaymentGateway {
    @Override
    public String initiatePayment(Long orderId) {
        return null;
    }
}

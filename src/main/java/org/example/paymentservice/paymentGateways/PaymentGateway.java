package org.example.paymentservice.paymentGateways;

import com.stripe.exception.StripeException;

public interface PaymentGateway {
    String initiatePayment(Long orderId) throws StripeException;
}

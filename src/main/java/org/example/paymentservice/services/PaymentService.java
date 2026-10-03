package org.example.paymentservice.services;

import com.stripe.exception.StripeException;
import org.example.paymentservice.paymentGateways.PaymentGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentGateway paymentGateway;

    public PaymentService(@Qualifier("Stripe") PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String initiatePayment(Long orderId) throws StripeException {
        //get order details
        //make a call to paymentGateway to generate paymentLink
        return paymentGateway.initiatePayment(orderId);
    }
}

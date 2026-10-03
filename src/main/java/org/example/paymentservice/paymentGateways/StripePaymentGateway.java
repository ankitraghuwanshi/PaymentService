package org.example.paymentservice.paymentGateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("Stripe")
public class StripePaymentGateway implements PaymentGateway {

    @Value("${Stripe.key}")
    private String apikey;

    @Override
    public String initiatePayment(Long orderId) throws StripeException {
        // Don't put any keys in code. See https://docs.stripe.com/keys-best-practices.
        // Find your keys at https://dashboard.stripe.com/apikeys.
        Stripe.apiKey = apikey;

        //create product
        ProductCreateParams productCreateParams =
                ProductCreateParams.builder().setName("Water Bottle").build();
        Product product = Product.create(productCreateParams);

        //create price
        PriceCreateParams priceCreateParams =
                PriceCreateParams.builder()
                        .setCurrency("INR") //Indian rupee
                        .setUnitAmount(100000L) //1000 per item
                        .setProduct(product.getId())
                        .build();

        Price price = Price.create(priceCreateParams);

        //create payment link
        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(  //setPrice and setQuantity
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(  //after completion with redirect url
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("https://google.com")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = PaymentLink.create(paymentLinkCreateParams);

        return paymentLink.getUrl();
    }
}

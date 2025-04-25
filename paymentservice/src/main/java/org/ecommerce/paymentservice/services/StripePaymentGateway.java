package org.ecommerce.paymentservice.services;

import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.stereotype.Service;

@Service("stripe")
public class StripePaymentGateway implements PaymentService{
    @Override
    public String generatePaymentLink(Long orderId) throws RazorpayException, StripeException {
        Stripe.apiKey = "sk_test_51QRu3gKHqOK4zXPGQDHLDPVt3T4dJmdHP90N6OcQN1HgQlXM3AqMLJ7ALUbtIJlb8QXmVyt3OencLSqTatwss00qan43qcq";

        PriceCreateParams priceCreateParams =
                PriceCreateParams.builder()
                        .setCurrency("INR")
                        .setUnitAmount(10000L)//100
                        .setRecurring(
                                PriceCreateParams.Recurring.builder()
                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                        .build()
                        )
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Payment for a five star chocolate").build()
                        )
                        .build();
        Price price = Price.create(priceCreateParams);

        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();
        PaymentLink paymentLink = PaymentLink.create(paymentLinkCreateParams);

        return paymentLink.toString();
    }
}

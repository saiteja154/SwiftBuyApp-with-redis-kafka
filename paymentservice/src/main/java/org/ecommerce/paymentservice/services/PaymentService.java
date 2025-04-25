package org.ecommerce.paymentservice.services;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.boot.configurationprocessor.json.JSONException;

public interface PaymentService {
    public String generatePaymentLink(Long orderId) throws RazorpayException, StripeException, JSONException;
}

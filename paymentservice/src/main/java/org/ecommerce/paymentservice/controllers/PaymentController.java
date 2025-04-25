package org.ecommerce.paymentservice.controllers;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

import org.ecommerce.paymentservice.dtos.GeneratePaymentLinkRequestDto;
import org.ecommerce.paymentservice.services.RazorpayPaymentGateway;
import org.ecommerce.paymentservice.services.StripePaymentGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private StripePaymentGateway stripePaymentGateway;
    private RazorpayPaymentGateway razorpayPaymentGateway;

    public PaymentController(StripePaymentGateway stripePaymentGateway, RazorpayPaymentGateway razorpayPaymentGateway){
        this.razorpayPaymentGateway = razorpayPaymentGateway;
        this.stripePaymentGateway = stripePaymentGateway;
    }

    @PostMapping("/payments")
    public String generatePaymentLink(@RequestBody GeneratePaymentLinkRequestDto generatePaymentLinkRequestDto) throws RazorpayException, StripeException {
        //Return the payment link
        //custom logic
        //2 payment gateways. 90% of transactions  - rp, 10% - stripe
//        if(rp turn){
//            rpservice.gener
//        }else {
//            stripepservoce.gener
//        }
        return stripePaymentGateway.generatePaymentLink(generatePaymentLinkRequestDto.orderId);
    }

    @PostMapping("/webhook")//PG will call this api on change of payment status
    public void handleWebHook(@RequestBody Object object){
        //here is where you decide what you want to do
        //you can go to razorpay dashboard and set these webhooks
    }

    //1000 + 100 + 18%
    //1100
    //MRP - inclusive
}

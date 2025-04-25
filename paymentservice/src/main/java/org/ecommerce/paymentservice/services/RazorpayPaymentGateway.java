package org.ecommerce.paymentservice.services;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;

import org.springframework.stereotype.Service;

@Service("razorpay")
public class RazorpayPaymentGateway implements PaymentService{
    private RazorpayClient razorpayClient;
    public RazorpayPaymentGateway(RazorpayClient razorpayClient){
        this.razorpayClient = razorpayClient;
    }
    @Override
    public String generatePaymentLink(Long orderId) throws RazorpayException {
        //Will I need to make a call to order details?
        //We will learn this in authentication module after machine coding is done(LLD-4)
        //through rest template we can make a call to order service

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000); //10 Rs 90.50 * 100 = 9050
        paymentLinkRequest.put("currency","INR");
        //paymentLinkRequest.put("accept_partial",true);
        //paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by", System.currentTimeMillis() + 10*60*1000); //epoch
        paymentLinkRequest.put("reference_id", orderId.toString());
        paymentLinkRequest.put("description","Payment for excited batch discussion");

        JSONObject customer = new JSONObject();
        customer.put("name","Sai Teja");
        customer.put("contact","+91 9707651234");
        customer.put("email","saiteja@gmail.com");

        paymentLinkRequest.put("customer",customer);

        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);

        paymentLinkRequest.put("reminder_enable",true);

        //JSONObject notes = new JSONObject();
        //notes.put("policy_name","Jeevan Bima");

        //paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://google.com");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return payment.toString();
    }
}

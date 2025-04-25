package org.ecommerce.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class PaymentserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentserviceApplication.class, args);
    }

}

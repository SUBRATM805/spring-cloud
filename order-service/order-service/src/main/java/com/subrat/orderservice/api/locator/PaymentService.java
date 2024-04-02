package com.subrat.orderservice.api.locator;

import com.subrat.orderservice.api.common.Payment;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${payment.url}")
    public String paymentUri;

    public static final String PAYMENT_SERVICE="paymentService";
    @CircuitBreaker(name =PAYMENT_SERVICE,fallbackMethod = "getAllAvailableProducts")
    public Payment doPayment(Payment payment){
        Payment paymentResponse = restTemplate.postForObject(paymentUri+"payment/doPayment", payment, Payment.class);
        return paymentResponse;
    }

    public Payment getAllAvailableProducts(Exception e){
        return Payment.builder().paymentStatus("inprocess").build();
    }
}

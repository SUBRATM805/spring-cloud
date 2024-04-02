package com.subrat.orderservice.api.service;

import com.subrat.orderservice.api.common.Payment;
import com.subrat.orderservice.api.common.TransationRequest;
import com.subrat.orderservice.api.common.TransationResponse;
import com.subrat.orderservice.api.entity.Order;
import com.subrat.orderservice.api.entity.ProductDetails;
import com.subrat.orderservice.api.locator.PaymentService;
import com.subrat.orderservice.api.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentService paymentService;



    @Transactional
    public TransationResponse saveOrder(TransationRequest transationRequest){
        String message = "";
        Order order = transationRequest.getOrder();
        Payment payment = transationRequest.getPayment();
        payment.setAmount(order.getPrice());
        Order orderPersist = Order.builder().price(order.getPrice())
                .name(order.getName())
                .quantity(order.getQuantity())
                .build();
        List<ProductDetails> collect = order.getProductDetails().stream()
                .map(p -> ProductDetails.builder()
                        .order(orderPersist)
                        .manufacturer(p.getManufacturer())
                        .productDescription(p.getProductDescription()).build())
                .collect(Collectors.toList());
        orderPersist.setProductDetails(collect);
        Order saveOrder = orderRepository.save(orderPersist);
        payment.setOrderId(saveOrder.getId());
        Payment paymentResponse = paymentService.doPayment(payment);
        message = paymentResponse.getPaymentStatus().equals("success") ? "Transation successfull" : "Transation failed";
        return TransationResponse.builder().order(order)
                .amount(paymentResponse.getAmount())
                .transactionId(paymentResponse.getTransactionId()).message(message).build();
    }
}

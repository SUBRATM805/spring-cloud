package com.subrat.paymentservice.api.controller;

import com.subrat.paymentservice.api.entity.Payment;
import com.subrat.paymentservice.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @PostMapping("/doPayment")
    public Payment processPayment(@RequestBody Payment payment){
        return paymentService.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderId(@PathVariable int orderId){
        return paymentService.findPaymentHistoryByOrderId(orderId);
    }

}

package com.subrat.paymentservice.api.service;

import com.subrat.paymentservice.api.entity.Payment;
import com.subrat.paymentservice.api.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public Payment doPayment(Payment payment){
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setAmount(payment.getAmount());
        return paymentRepository.save(payment);
    }

    public String paymentProcessing(){
        return  new Random().nextBoolean()?"success" : "failure";
    }

    public Payment findPaymentHistoryByOrderId(int orderId) {
        return paymentRepository.findByOrderId(orderId);
    }
}

package com.subrat.orderservice.api.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payment {

    private int paymentId;
    private String paymentStatus;
    private String transactionId;
    private int orderId;
    private double amount;
}

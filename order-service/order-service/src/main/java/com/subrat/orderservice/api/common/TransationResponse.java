package com.subrat.orderservice.api.common;

import com.subrat.orderservice.api.entity.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransationResponse {

    private Order order;
    private double amount;
    private String transactionId;
    private String message;
}

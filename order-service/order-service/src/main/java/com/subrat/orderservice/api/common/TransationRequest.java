package com.subrat.orderservice.api.common;

import com.subrat.orderservice.api.entity.Order;
import lombok.Data;

@Data
public class TransationRequest {

    private Order order;
    private Payment payment;
}

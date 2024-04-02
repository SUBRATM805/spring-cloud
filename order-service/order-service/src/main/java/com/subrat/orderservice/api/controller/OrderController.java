package com.subrat.orderservice.api.controller;

import com.subrat.orderservice.api.common.TransationRequest;
import com.subrat.orderservice.api.common.TransationResponse;
import com.subrat.orderservice.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {



    @Autowired
    private OrderService orderService;

    @PostMapping("/bookorder")
    public TransationResponse bookOrder(@RequestBody TransationRequest transationRequest){
        return orderService.saveOrder(transationRequest);
    }


}

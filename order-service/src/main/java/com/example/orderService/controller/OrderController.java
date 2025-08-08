package com.example.orderService.controller;


import com.example.orderService.dto.request.CreateOrderRequest;
import com.example.orderService.dto.response.OrderResponse;
import com.example.orderService.service.impl.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    public OrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request){
        return orderService.placeOrder(request);
    }


}

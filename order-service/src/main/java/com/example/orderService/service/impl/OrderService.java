package com.example.orderService.service.impl;

import com.example.orderService.dto.request.CreateOrderRequest;
import com.example.orderService.dto.response.OrderResponse;
import com.example.orderService.repository.OrderRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository repository;
    private final RestTemplate restTemplate;

    public OrderResponse placeOrder(@Valid CreateOrderRequest request) {
        return
    }
}

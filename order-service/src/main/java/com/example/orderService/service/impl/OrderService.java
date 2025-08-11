package com.example.orderService.service.impl;

import com.example.orderService.dto.request.CreateOrderRequest;
import com.example.orderService.dto.response.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {

    List<OrderResponse> getAllOrders();

    OrderResponse placeOrder(CreateOrderRequest request);

    Optional<OrderResponse> getOrderById(String id);
}

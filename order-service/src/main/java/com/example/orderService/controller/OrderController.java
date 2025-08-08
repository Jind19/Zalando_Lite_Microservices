package com.example.orderService.controller;


import com.example.orderService.dto.request.CreateOrderRequest;
import com.example.orderService.dto.response.OrderResponse;
import com.example.orderService.service.impl.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    /**
     * POST endpoint to create an order.
     */
    @PostMapping
    public OrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
        // Call order service and return response
        return service.placeOrder(request);
    }

    @GetMapping
    public List<OrderResponse> getAllOrders(){
        return service.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable String id){
        return service.getOrderById(id);
    }
}

package com.example.orderService.service.impl;

import com.example.orderService.dto.request.CreateOrderRequest;
import com.example.orderService.dto.response.OrderResponse;
import com.example.orderService.mapper.OrderMapper;
import com.example.orderService.repository.OrderRepository;
import com.example.orderService.service.client.InventoryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final InventoryClient inventoryClient;

    @Override
    public List<OrderResponse> getAllOrders() {
        return repository.findAll()
                .stream()
                .map(mapper::toOrderResponse)
                .toList();
    }

    public OrderResponse placeOrder(CreateOrderRequest request) {

        // 1. Validate stock for each item
        for (var item : request.items()) {
            boolean available = inventoryClient.isStockAvailable(item.productId(), item.quantity());
            if (!available) {
                throw new IllegalArgumentException("Insufficient stock for product ID: " + item.productId());
            }
        }

        // 2. Map request to order entity
        var order = mapper.toEntity(request);

        // 3. Save order to DB
        var savedOrder = repository.save(order);

        // 4. Deduct stock for each item after order saved
        for (var item : request.items()) {
            inventoryClient.deductStock(item.productId(), item.quantity());
        }

        // 5. Map saved order to response DTO and return
        return mapper.toOrderResponse(savedOrder);
    }


    @Override
    public OrderResponse getOrderById(String id) {
        return repository.findById(id)
                .map(mapper::toOrderResponse)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
    }
}

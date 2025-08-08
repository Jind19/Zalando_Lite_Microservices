package com.example.orderService.mapper;

import com.example.orderService.dto.request.CreateOrderRequest;
import com.example.orderService.dto.response.OrderLineItemResponse;
import com.example.orderService.dto.response.OrderResponse;
import com.example.orderService.model.Order;
import com.example.orderService.model.OrderLineItem;
import com.example.orderService.service.client.InventoryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final InventoryClient client;

    public Order toEntity(CreateOrderRequest request) {


        List<OrderLineItem> items = request.items()
                .stream()
                .map(
                        item -> new OrderLineItem(
                                item.productId(),
                                item.quantity()
                        )
                ).toList();

        return new Order(items); // id & createdAt set later
    }

    public OrderResponse toOrderResponse(Order order) {
        List<OrderLineItemResponse> itemResponses = order.getItems()
                .stream()
                .map(
                        item -> new OrderLineItemResponse(
                                item.getProductId(),
                                item.getQuantity()
                        )
                ).toList();

        return new OrderResponse(
                order.getId(),
                order.getCreatedAt(),
                itemResponses,
                order.getTotalPrice(),
                order.getDiscountApplied()
        );
    }
}
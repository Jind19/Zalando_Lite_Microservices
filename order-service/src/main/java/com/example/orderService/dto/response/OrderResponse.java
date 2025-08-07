package com.example.orderService.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        String id,
        LocalDateTime createdAt,
        List<OrderLineItemResponse> items,
        double totalPrice,
        double discountApplied
) {}
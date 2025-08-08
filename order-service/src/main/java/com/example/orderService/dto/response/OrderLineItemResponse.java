package com.example.orderService.dto.response;

/**
 * A single item inside an order response.
 */
public record OrderLineItemResponse(
        Long productId,
        int quantity
) {}
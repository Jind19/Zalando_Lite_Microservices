package com.example.orderService.dto.response;

/**
 * A single item inside an order response.
 */
public record OrderLineItemResponse(
        Long productId,
        int quantity,
        double price,           // Optional: price per item
        double discountedPrice  // Optional: price after discount
) {}
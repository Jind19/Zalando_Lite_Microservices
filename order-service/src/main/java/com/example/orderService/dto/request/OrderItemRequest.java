package com.example.orderService.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * A single item in the order request.
 */
public record OrderItemRequest(
        @NotNull Long productId,
        @Min(1) int quantity
) {}
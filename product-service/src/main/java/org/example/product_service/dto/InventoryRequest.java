package org.example.product_service.dto;

import lombok.Data;

@Data
public class InventoryRequest {
    private Long productId;
    private Integer quantity;

}

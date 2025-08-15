package org.example.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for exposing product data to clients, including stock quantity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private double price;
    private String description;
    private int quantity; // stock quantity
}

package org.example.product_service.dto;

import jdk.jfr.DataAmount;
import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String category;
    private String description;
    private Double price;
    private int quantity; // to check stock
    private String customerId;  // to fetch internal token
}

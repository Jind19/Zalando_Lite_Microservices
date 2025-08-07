package com.example.orderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderLineItem {

    private Long productId;

    private int quantity;

    private double pricePerUnit;
}

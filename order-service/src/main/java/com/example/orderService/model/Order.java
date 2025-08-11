package com.example.orderService.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Document(collection = "orders")
public class Order {

    private String id;

    private LocalDateTime createdAt = LocalDateTime.now();

    private List<OrderLineItem> items = new ArrayList<>();

    // Final price after discount
    private double totalPrice;

    // Total discount amount or rate applied
    private double discountApplied;

    public Order(List<OrderLineItem> items) {
        this.items = items;
        this.createdAt = LocalDateTime.now();
    }

}

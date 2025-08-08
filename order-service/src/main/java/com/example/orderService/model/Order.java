package com.example.orderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
@Document(collection = "orders")
public class Order {

    private String Id;

    private LocalDateTime createdAt;

    private List<OrderLineItem> items;

    // Final price after discount
    private double totalPrice;

    // Total discount amount or rate applied
    private double discountApplied;

    public Order(List<OrderLineItem> items) {
    }

}

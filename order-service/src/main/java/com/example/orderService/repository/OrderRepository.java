package com.example.orderService.repository;

import com.example.orderService.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface OrderRepository extends MongoRepository<Order, String> {
    // Basic CRUD methods inherited
}

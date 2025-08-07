package com.example.inventoryService.repository;

import com.example.inventoryService.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * MongoDB repository for inventory records.
 */
public interface InventoryRepository extends MongoRepository<Inventory, String> {

    /**
     * Finds inventory by product ID.
     *
     * @param productId the ID of the product
     * @return an optional inventory record
     */
    Optional<Inventory> findByProductId(Long productId);
}

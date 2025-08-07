package com.example.inventoryService.service;

import com.example.inventoryService.model.Inventory;
import com.example.inventoryService.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class that contains the business logic for inventory management.
 */
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;

    /**
     * Creates or updates the inventory entry for a product.
     *
     * @param productId the product ID
     * @param quantity  the new stock quantity
     */
    public void createOrUpdateStock(Long productId, int quantity) {
        Inventory inventory = repository.findByProductId(productId)
                .orElse(new Inventory());
        inventory.setProductId(productId);
        inventory.setQuantity(quantity);
        repository.save(inventory);
    }

    /**
     * Gets the current quantity in stock for the given product.
     *
     * @param productId the product ID
     * @return current stock quantity (0 if not found)
     */
    public int getStock(Long productId){
        Optional<Inventory> inventory = repository.findByProductId(productId);
        int stock =  inventory.map(Inventory::getQuantity).orElse(0);
        return stock;
    }
}

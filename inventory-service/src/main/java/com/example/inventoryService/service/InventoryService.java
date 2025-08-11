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


    /**
     * Deducts a given quantity from the stock of the specified product.
     *
     * @param productId the ID of the product whose stock will be reduced
     * @param quantity  the quantity to deduct (must be greater than 0)
     * @throws RuntimeException if the product does not exist or if there is not enough stock
     */
    public void deductStock(Long productId, int quantity) {
        Inventory inventory = repository.findByProductId(productId)
                .orElseThrow( () -> new IllegalArgumentException("Product not found: " + productId));

        //Check if stock is sufficient
        if( inventory.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough stock for product: " + productId);
        }

        // Deduct the quantity
        inventory.setQuantity(inventory.getQuantity() - quantity);

        // Save the updated inventory back to the repository
        repository.save(inventory);


    }
}

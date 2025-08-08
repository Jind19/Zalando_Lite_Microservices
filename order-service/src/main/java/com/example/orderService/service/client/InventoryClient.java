package com.example.orderService.service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * REST client to communicate with Inventory-Service.
 * Verifies stock availability before placing an order.
 */
@Component
@RequiredArgsConstructor
public class InventoryClient {

    private final RestTemplate restTemplate;

    @Value("${inventory.service.url")
    private String inventoryServiceUrl;

    /**
     * Checks if the inventory has enough stock for the given productId and quantity.
     *
     * @param productId Product ID to check.
     * @param quantity  Required quantity.
     * @return true if stock is sufficient, false otherwise.
     */
    public boolean isStockAvailable(Long productId, int quantity) {
        try {
            Integer availableStock = restTemplate.getForObject(
                    inventoryServiceUrl + "/" + productId, Integer.class);
            return availableStock != null && availableStock >= quantity;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Failed to check stock for product " + productId, e);
        }
    }
}

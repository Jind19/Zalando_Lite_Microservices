package com.example.orderService.service.client;

import org.springframework.http.*; // Imports what is needed for working with HTTP requests / responses
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * REST client to communicate with Inventory-Service.
 * Verifies stock availability before placing an order.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryClient {

    private final RestTemplate restTemplate;

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    /**
     * Internal DTO for POST request body.
     */
    public record InventoryRequest(Long productId, int quantity) {}

    /**
     * Extracts Authorization header from current HTTP request and prepares headers for downstream call.
     */
    private HttpHeaders getAuthHeaders() {

        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if(attributes==null){
            log.warn("No request attributes found. Possibly called outside HTTP request context.");
            throw new IllegalStateException("Cannot get Authorization header outside of an HTTP request");
        }

        HttpServletRequest currentRequest = attributes.getRequest();

        String token = currentRequest.getHeader("Authorization");

        if (token == null || token.isBlank()) {
            log.warn("Authorization header is missing in the incoming request.");
            throw new IllegalStateException("Authorization token is missing");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);

        return headers;

    }

    public boolean isStockAvailable(Long productId, int requiredQuantity){
        HttpEntity<Void> entity = new HttpEntity<>(getAuthHeaders());
        ResponseEntity<Integer> response = restTemplate.exchange(
                inventoryServiceUrl + "/" + productId,
                HttpMethod.GET,
                entity,
                Integer.class
        );
        Integer available = response.getBody();
        return available != null && available >= requiredQuantity;

    }

    /**
     * Deducts the given quantity from the product's stock in the inventory.
     */
    public void deductStock(Long productId, int quantity) {

        InventoryRequest body = new InventoryRequest(productId, quantity);

        HttpEntity<InventoryRequest> entity = new HttpEntity<>(body, getAuthHeaders());

        restTemplate.exchange(
                inventoryServiceUrl + "/deduct",
                HttpMethod.POST,
                entity,
                Void.class
        );

    }

}

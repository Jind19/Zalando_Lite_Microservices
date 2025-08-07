package com.example.inventoryService.controller;

import com.example.inventoryService.dto.InventoryRequest;
import com.example.inventoryService.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/inventory")
@RestController
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping({ "", "/" })
    public void createOrUpdateInventory(@RequestBody InventoryRequest inventoryRequest) {
        inventoryService.createOrUpdateStock(inventoryRequest.productId(), inventoryRequest.quantity());
    }

    /**
     * GET /{productId} — returns the current stock for a product.
     */
    @GetMapping("/{productId}")
    public int getStock(@PathVariable Long productId){
       return inventoryService.getStock(productId);
    }

}

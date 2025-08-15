package org.example.product_service.controller;

import org.example.product_service.dto.ProductDto;

import lombok.RequiredArgsConstructor;
import org.example.product_service.model.Product;
import org.example.product_service.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST controller exposing product-related HTTP endpoints.
 * By default, our SecurityConfig already enforces that every request must be authenticated.
 * Without an Authorization: Bearer <token> header:
 * We get 401 Unauthorized.
 */

@RequestMapping("/products")
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService service;

    /**
     * Internal request wrapper for product + quantity in POST body.
     */
    public record ProductWithQuantity(Product product, int quantity) {}

    /**
     * GET /products — fetch all products with live stock quantity.
     */
    @GetMapping
    public List<ProductDto> getAllProducts() {
        return service.getAllProducts();
    }

     /**
      * POST /products — add a product and initialize its inventory.
     */
    @PostMapping
    public  ProductDto addProduct(@RequestBody ProductWithQuantity request) {
            return service.addProduct(request.product(), request.quantity());
        }

}
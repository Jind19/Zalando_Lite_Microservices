package org.example.product_service.service;

import lombok.RequiredArgsConstructor;

import  org.example.product_service.dto.ProductDto;
import org.example.product_service.mapper.ProductMapper;
import org.example.product_service.model.Product;
import org.example.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

   private final ProductRepository repository;
   private final InventoryClient inventoryClient;

    public  List<ProductDto> getAllProducts() {
        List<Product> products = repository.findAll();
        List<ProductDto> productsDto =  products
                .stream()
                .map(
                        product -> ProductMapper.toDTO(product,inventoryClient.getStockQuantity(product.getId()))
                )
                .toList();
        return productsDto;
    }

    public ProductDto addProduct(Product product, int quantity) {
        Optional<Product> existing = repository.findByName(product.getName());
        if (existing.isPresent()){
            throw new RuntimeException("Product already exists !!!");
        }
        Product saved = repository.save(product);
        inventoryClient.createInventory(saved.getId(), quantity);
        return ProductMapper.toDTO(saved, quantity);
    }
}

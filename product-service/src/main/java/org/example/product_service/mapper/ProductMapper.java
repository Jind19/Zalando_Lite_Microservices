package org.example.product_service.mapper;

import org.example.product_service.dto.ProductDto;
import org.example.product_service.model.Product;

/**
 * Utility class for converting between Product entity and ProductDto.
 */
public class ProductMapper {

    public static ProductDto toDTO(Product product, int quantity){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                quantity
        );
    }
}

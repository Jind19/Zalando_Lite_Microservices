package org.example.product_service.repository;

import org.example.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>{

    /**
     * Find a product by its name.
     *
     * @param name product name
     * @return Optional containing the product if found
     */
    Optional<Product> findByName(String name);
}

package org.example.product_service.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * JPA maps the entity to a products table in PostgreSQL.
 * @Builder allows flexible object creation - e.g Product.builder().name("Jacket").build()
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;

}

package com.e_sell.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    
    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}

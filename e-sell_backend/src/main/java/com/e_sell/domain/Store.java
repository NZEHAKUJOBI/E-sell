package com.e_sell.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String name;
    private String description;
    private String storeUrl;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserInfo owner;
    
    @OneToMany(mappedBy = "store")
    private Set<Product> products;
}

package com.e_sell.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String username;
    private String email;
    private String password;
    private String role;
    private String  address;
    @Column(name="phone")
    private String phoneNumber;
    private Date createdAt; 
    
    
    @OneToMany(mappedBy = "owner")
    private Set<Store> stores;
}

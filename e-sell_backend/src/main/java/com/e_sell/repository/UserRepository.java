package com.e_sell.repository;

import com.e_sell.domain.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByEmail(String email);
    UserInfo findByUsername(String username);
    boolean existsByEmail(String email);
}

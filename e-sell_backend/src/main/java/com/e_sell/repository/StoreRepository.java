package com.e_sell.repository;

import com.e_sell.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByStoreUrl(String storeUrl);
}

package com.e_sell.service;

import com.e_sell.domain.Store;
import com.e_sell.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }
    
    public Store getStoreByUrl(String storeUrl) {
        return storeRepository.findByStoreUrl(storeUrl);
    }
}

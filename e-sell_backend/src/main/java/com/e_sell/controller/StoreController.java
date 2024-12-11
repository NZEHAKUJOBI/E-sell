package com.e_sell.controller;

import com.e_sell.domain.Store;
import com.e_sell.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    
    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody Store store) {
        return ResponseEntity.ok(storeService.createStore(store));
    }
    
    @GetMapping("/{storeUrl}")
    public ResponseEntity<Store> getStore(@PathVariable String storeUrl) {
        return ResponseEntity.ok(storeService.getStoreByUrl(storeUrl));
    }
}

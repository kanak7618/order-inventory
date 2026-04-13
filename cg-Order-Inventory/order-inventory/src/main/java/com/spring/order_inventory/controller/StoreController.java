package com.spring.order_inventory.controller;

import com.spring.order_inventory.dto.OrderResponseDto;
import com.spring.order_inventory.dto.StoreResponseDto;
import com.spring.order_inventory.entity.Order;
import com.spring.order_inventory.service.IStoreService;
import com.spring.order_inventory.service.IStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final IStoreService istoreService;


    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponseDto> getStoreById(@PathVariable Integer storeId) {
        return ResponseEntity.ok(istoreService.getStoreById(storeId));
    }
    @GetMapping("/{storeId}/orders")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByStoreId(@PathVariable Integer storeId) {
        return ResponseEntity.ok(istoreService.getOrdersByStoreId(storeId));
    }
}
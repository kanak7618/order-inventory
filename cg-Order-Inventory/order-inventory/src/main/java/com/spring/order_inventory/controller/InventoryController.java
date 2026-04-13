package com.spring.order_inventory.controller;

import com.spring.order_inventory.dto.InventoryDetailsDto;
import com.spring.order_inventory.dto.OrderResponseDto;
import com.spring.order_inventory.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final IInventoryService iInventoryService;
    @Autowired
    public InventoryController(IInventoryService iInventoryService) {
        this.iInventoryService = iInventoryService;
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<InventoryDetailsDto>> getInventoryByStoreId(@PathVariable Integer storeId) {
        return ResponseEntity.ok(iInventoryService.findByStoreId(storeId));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<InventoryDetailsDto>> getInventoryByProductId(@PathVariable Integer productId) {
        return ResponseEntity.ok(iInventoryService.findByProductId(productId));
    }




}

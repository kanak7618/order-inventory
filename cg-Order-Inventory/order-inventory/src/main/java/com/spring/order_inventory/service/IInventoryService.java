package com.spring.order_inventory.service;

import  com.spring.order_inventory.dto.InventoryDetailsDto;
import com.spring.order_inventory.dto.InventoryResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IInventoryService {

    List<InventoryDetailsDto> findByStoreId(Integer storeId);

    List<InventoryDetailsDto> findByProductId(Integer productId);
}
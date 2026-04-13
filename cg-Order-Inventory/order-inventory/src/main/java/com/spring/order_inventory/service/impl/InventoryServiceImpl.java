package com.spring.order_inventory.service.impl;

import com.spring.order_inventory.dto.InventoryDetailsDto;
import com.spring.order_inventory.exception.InvalidFormatException;
import com.spring.order_inventory.repository.InventoryRepository;
import com.spring.order_inventory.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements IInventoryService {
    private final InventoryRepository inventoryRepository;


    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<InventoryDetailsDto> findByStoreId(Integer storeId) {
        if(storeId<0)
            throw  new InvalidFormatException(" id should be greater than 0");

        return inventoryRepository.findByStoreId(storeId);
    }

    @Override
    public List<InventoryDetailsDto> findByProductId(Integer productId) {
        if(productId<0)
            throw  new InvalidFormatException(" id should be greater than 0");

        return inventoryRepository.findByProductId(productId);
    }

}

package com.spring.order_inventory.service;

import com.spring.order_inventory.dto.OrderResponseDto;
import com.spring.order_inventory.dto.StoreResponseDto;
import com.spring.order_inventory.entity.Order;
import java.util.List;
public interface IStoreService {
    StoreResponseDto getStoreById(Integer storeId);

    List<OrderResponseDto> getOrdersByStoreId(Integer storeId);
}

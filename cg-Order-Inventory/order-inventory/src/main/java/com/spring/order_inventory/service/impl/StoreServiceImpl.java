package com.spring.order_inventory.service.impl;

import com.spring.order_inventory.dto.StoreResponseDto;
import com.spring.order_inventory.entity.Store;
import com.spring.order_inventory.exception.IdNotFoundException;
import com.spring.order_inventory.exception.InvalidFormatException;
import com.spring.order_inventory.repository.OrderRepository;
import com.spring.order_inventory.repository.StoreRepository;
import com.spring.order_inventory.service.IStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.spring.order_inventory.entity.Order;
import java.util.List;
import com.spring.order_inventory.dto.OrderResponseDto;

@Service
@RequiredArgsConstructor
public class  StoreServiceImpl implements IStoreService {

    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;


    @Override
    public StoreResponseDto getStoreById(Integer storeId) {
        if (storeId<0){
            throw new InvalidFormatException("ID entered is less than 0, please enter valid ID");
        }
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IdNotFoundException("ID not found in stores table"));


        return StoreResponseDto.builder()
                .storeId(store.getStoreId())
                .storeName(store.getStoreName())
                .webAddress(store.getWebAddress())
                .physicalAddress(store.getPhysicalAddress())
                .latitude(store.getLatitude())
                .longitude(store.getLongitude())
                .build();

    }

    @Override
    public List<OrderResponseDto> getOrdersByStoreId(Integer storeId) {
        if (storeId<0){
            throw new InvalidFormatException("ID entered is less than 0, please enter valid ID");
        }
        List<Order> orders = orderRepository.findByStoreStoreId(storeId);

        return orders.stream()
                .map(order -> OrderResponseDto.builder()
                        .orderId(order.getOrderId())
                        .orderTms(order.getOrderTms())
                        .orderStatus(order.getOrderStatus())
                        .customerId(order.getCustomer().getCustomerId())
                        .customerName(order.getCustomer().getFullName())
                        .storeId(order.getStore().getStoreId())
                        .storeName(order.getStore().getStoreName())
                        .build())
                .toList();
    }
}
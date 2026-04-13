package com.spring.order_inventory.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.order_inventory.dto.OrderResponseDto;
import com.spring.order_inventory.dto.StoreResponseDto;
import com.spring.order_inventory.entity.Order;
import com.spring.order_inventory.entity.Store;
import com.spring.order_inventory.exception.IdNotFoundException;
import com.spring.order_inventory.mapper.OrderMapper;
import com.spring.order_inventory.repository.OrderRepository;
import com.spring.order_inventory.service.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {
	private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderResponseDto getOrderById(Integer id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Order not found with id: " + id));

        return OrderMapper.toDto(order);
    }

    
    public StoreResponseDto getStoreByOrderId(Integer orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IdNotFoundException("Order not found with id: " + orderId));

        Store store = order.getStore();

        return StoreResponseDto.builder()
                .storeId(store.getStoreId())
                .storeName(store.getStoreName())
                .build();
    }

    public List<OrderResponseDto> getOrdersByCustomerId(Integer customerId) {

        List<Order> orders = orderRepository.findByCustomerCustomerId(customerId);

        return OrderMapper.toDtoList(orders);
    }
    
    @Override
    public Page<OrderResponseDto> getOrdersPageByCustomerId(Integer customerId, int page, int size) {

//        Pageable pageable = PageRequest.of(page, size);
    	
    	int correctedPage = (page > 0) ? page - 1 : 0;
    	Pageable pageable = PageRequest.of(correctedPage, size);

        Page<Order> orderPage = orderRepository
                .findOrdersPageByCustomerId(customerId, pageable);

        //  Use your existing mapper
        List<OrderResponseDto> dtoList =
                OrderMapper.toDtoList(orderPage.getContent());

        return new PageImpl<>(dtoList, pageable, orderPage.getTotalElements());
    }
    
    
    
    public List<OrderResponseDto> getOrdersByStoreId(Integer storeId) {

        List<Order> orders = orderRepository.findByStoreStoreId(storeId);

        return OrderMapper.toDtoList(orders);
    }
	
}

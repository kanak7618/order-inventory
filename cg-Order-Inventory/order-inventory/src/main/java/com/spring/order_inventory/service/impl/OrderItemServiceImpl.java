package com.spring.order_inventory.service.impl;

import com.spring.order_inventory.dto.OrderItemResponseDto;
import com.spring.order_inventory.entity.OrderItem;
import com.spring.order_inventory.mapper.OrderItemMapper;
import com.spring.order_inventory.repository.OrderItemRepository;
import com.spring.order_inventory.service.IOrderItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements IOrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;


    //  1. GET ALL ORDER ITEMS

    @Override
    public List<OrderItemResponseDto> getAllOrderItemDetails() {
        try {
            List<Object[]> rows = orderItemRepository.findAllOrderItemsRaw();

            return rows.stream().map(row -> {
                OrderItemResponseDto dto = new OrderItemResponseDto();
                dto.setLineItemId((Integer) row[0]);
                dto.setOrderId((Integer) row[1]);
                dto.setProductId((Integer) row[2]);
                dto.setProductName((String) row[3]);
                dto.setUnitPrice(((Number) row[4]).doubleValue());
                dto.setQuantity((Integer) row[5]);
                dto.setShipmentId(row[6] != null ? (Integer) row[6] : null);
                return dto;
            }).toList();

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all order items", e);
        }
    }


    // 2. GET BY SHIPMENT ID

    @Override
    public List<OrderItemResponseDto> getByShipmentId(Integer shipmentId) {
        try {
            List<Object[]> rows = orderItemRepository.findAllByShipmentRaw(shipmentId);

            return rows.stream().map(row -> {
                OrderItemResponseDto dto = new OrderItemResponseDto();
                dto.setLineItemId((Integer) row[0]);
                dto.setOrderId((Integer) row[1]);
                dto.setProductId((Integer) row[2]);
                dto.setProductName((String) row[3]);
                dto.setShipmentId((Integer) row[4]);
                dto.setUnitPrice(((Number) row[5]).doubleValue());
                dto.setQuantity((Integer) row[6]);
                return dto;
            }).toList();

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch by shipmentId", e);
        }
    }


    // 3. GET BY ORDER ID

    @Override
    public List<OrderItemResponseDto> getByOrderId(Integer orderId) {
        try {
            return orderItemRepository.findByOrder_OrderId(orderId)
                    .stream()
                    .map(OrderItemMapper::toDTO)
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch by orderId", e);
        }
    }
}
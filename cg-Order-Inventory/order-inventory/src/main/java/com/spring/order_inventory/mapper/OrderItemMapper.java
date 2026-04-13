package com.spring.order_inventory.mapper;

import com.spring.order_inventory.dto.OrderItemResponseDto;
import com.spring.order_inventory.entity.OrderItem;

public class OrderItemMapper {

    public static OrderItemResponseDto toDTO(OrderItem orderItem) {

        OrderItemResponseDto dto = new OrderItemResponseDto();

        dto.setLineItemId(orderItem.getLineItemId());
        dto.setUnitPrice(orderItem.getUnitPrice());
        dto.setQuantity(orderItem.getQuantity());

        if (orderItem.getOrder() != null) {
            dto.setOrderId(orderItem.getOrder().getOrderId());
        }

        if (orderItem.getProduct() != null) {
            dto.setProductId(orderItem.getProduct().getProductId());
            dto.setProductName(orderItem.getProduct().getProductName());
        }

        if (orderItem.getShipment() != null) {
            dto.setShipmentId(orderItem.getShipment().getShipmentId());
        }

        return dto;
    }
}
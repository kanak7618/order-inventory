package com.spring.order_inventory.mapper;

import java.util.ArrayList;
import java.util.List;

import com.spring.order_inventory.dto.OrderItemResponseDto;
import com.spring.order_inventory.dto.OrderResponseDto;
import com.spring.order_inventory.entity.Order;
import com.spring.order_inventory.entity.OrderItem;

public class OrderMapper {
	private OrderMapper() {}

    public static OrderResponseDto toDto(Order order) {

        if (order == null) {
            return null;
        }

        // Prepare item list
        List<OrderItemResponseDto> itemDtos = new ArrayList<>();

        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {

                OrderItemResponseDto itemDto = OrderItemResponseDto.builder()
                        .orderId(item.getLineItemId())
                        .productId(item.getProduct() != null ? item.getProduct().getProductId() : null)
                        .productName(item.getProduct() != null ? item.getProduct().getProductName() : null)
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .build();

                itemDtos.add(itemDto);
            }
        }

        // Build main DTO
        return OrderResponseDto.builder()
                .orderId(order.getOrderId())
                .orderTms(order.getOrderTms())
                .orderStatus(order.getOrderStatus())

                // Customer
                .customerId(order.getCustomer() != null ? order.getCustomer().getCustomerId() : null)
                .customerName(order.getCustomer() != null ? order.getCustomer().getFullName() : null)

                // Store
                .storeId(order.getStore() != null ? order.getStore().getStoreId() : null)
                .storeName(order.getStore() != null ? order.getStore().getStoreName() : null)

                // Items
                .items(itemDtos)

                .build();
    }

    
    public static List<OrderResponseDto> toDtoList(List<Order> orders) {

        List<OrderResponseDto> dtoList = new ArrayList<>();

        if (orders != null) {
            for (Order order : orders) {
                dtoList.add(toDto(order));
            }
        }

        return dtoList;
    }
}

package com.spring.order_inventory.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.spring.order_inventory.constants.OrderStatus;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class OrderResponseDto {
	private Integer orderId;

    private LocalDateTime orderTms;

    private OrderStatus orderStatus;

    private Integer customerId;
    private String customerName;

    private Integer storeId;
    private String storeName;

    private List<OrderItemResponseDto> items;
}

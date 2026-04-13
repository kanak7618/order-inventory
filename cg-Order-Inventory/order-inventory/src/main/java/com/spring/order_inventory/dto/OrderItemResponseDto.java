package com.spring.order_inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponseDto {

    private Integer lineItemId;
    private Integer orderId;
    private Integer productId;
    private String productName;
    private Integer shipmentId;
    private Double unitPrice;
    private Integer quantity;
}
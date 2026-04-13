package com.spring.order_inventory.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShipmentResponseDto {

    private Integer shipmentId;
    private String shipmentStatus;

    // Customer
    private Integer customerId;
    private String customerName;

    // Store
    private Integer storeId;
    private String storeName;
}
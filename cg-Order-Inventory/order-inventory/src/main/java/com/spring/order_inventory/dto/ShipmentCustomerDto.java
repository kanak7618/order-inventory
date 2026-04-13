package com.spring.order_inventory.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ShipmentCustomerDto {

    private Integer shipmentId;
    private String shipmentStatus;

    private String deliveryStatus;

    private Integer storeId;
    private String storeName;
}
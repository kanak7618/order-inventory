package com.spring.order_inventory.mapper;

import com.spring.order_inventory.dto.ShipmentCustomerDto;
import com.spring.order_inventory.dto.ShipmentResponseDto;
import com.spring.order_inventory.entity.Shipment;

public class ShipmentMapper {

    public static ShipmentResponseDto toDto(Shipment s) {

        return ShipmentResponseDto.builder()
                .shipmentId(s.getShipmentId())
                .shipmentStatus(s.getShipmentStatus())

                //DIRECT ACCESS
                .customerId(s.getCustomer().getCustomerId())
                .customerName(s.getCustomer().getFullName())

                .storeId(s.getStore().getStoreId())
                .storeName(s.getStore().getStoreName())

                .build();
    }
    public static ShipmentCustomerDto toCustomerDto(Shipment s) {

        return ShipmentCustomerDto.builder()
                .shipmentId(s.getShipmentId())
                .shipmentStatus(s.getShipmentStatus())

                //  No delivery entity → using shipmentStatus
                .deliveryStatus(s.getShipmentStatus())

                .storeId(s.getStore().getStoreId())
                .storeName(s.getStore().getStoreName())

                .build();
    }
}
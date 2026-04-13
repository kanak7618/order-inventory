package com.spring.order_inventory.service;

import com.spring.order_inventory.dto.ShipmentCustomerDto;
import com.spring.order_inventory.dto.ShipmentResponseDto;
import java.util.List;

public interface IShipmentService {

    ShipmentResponseDto getShipmentById(Integer shipmentId);

    List<ShipmentCustomerDto> getShipmentsByCustomer(Integer customerId);
}
package com.spring.order_inventory.controller;

import com.spring.order_inventory.dto.ShipmentCustomerDto;
import com.spring.order_inventory.dto.ShipmentResponseDto;
import com.spring.order_inventory.service.IShipmentService;
import com.spring.order_inventory.service.impl.ShipmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final IShipmentService shipmentService;

    //GET /api/shipments/{shipmentId}
    @GetMapping("/{shipmentId}")
    public ShipmentResponseDto getShipment(@PathVariable Integer shipmentId) {
        return shipmentService.getShipmentById(shipmentId);
    }

    //GET /api/shipments/customer/{customerId}
    @GetMapping("/customer/{customerId}")
    public List<ShipmentCustomerDto> getShipmentsByCustomer(@PathVariable Integer customerId) {
        return shipmentService.getShipmentsByCustomer(customerId);
    }
}
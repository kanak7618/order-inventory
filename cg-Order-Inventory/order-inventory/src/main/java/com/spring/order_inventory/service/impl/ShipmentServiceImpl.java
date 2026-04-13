package com.spring.order_inventory.service.impl;

import com.spring.order_inventory.dto.ShipmentCustomerDto;
import com.spring.order_inventory.dto.ShipmentResponseDto;
import com.spring.order_inventory.entity.Shipment;
import com.spring.order_inventory.mapper.ShipmentMapper;
import com.spring.order_inventory.repository.ShipmentRepository;
import com.spring.order_inventory.service.IShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShipmentServiceImpl implements IShipmentService {

    private final ShipmentRepository shipmentRepository;

    //API 1
    @Override
    public ShipmentResponseDto getShipmentById(Integer id) {

        Shipment shipment = shipmentRepository.findShipmentWithDetails(id)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));

        return ShipmentMapper.toDto(shipment);
    }

    //API 2
    @Override
    public List<ShipmentCustomerDto> getShipmentsByCustomer(Integer customerId) {

        List<Shipment> shipments = shipmentRepository.findByCustomerId(customerId);

        return shipments.stream()
                .map(ShipmentMapper::toCustomerDto)
                .toList();
    }
}
package com.spring.order_inventory.controller;

import com.spring.order_inventory.dto.OrderItemResponseDto;
import com.spring.order_inventory.service.IOrderItemService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final IOrderItemService orderItemService;

    @GetMapping("/details")
    public ResponseEntity<List<OrderItemResponseDto>> getAllOrderItemDetails() {
        return ResponseEntity.ok(orderItemService.getAllOrderItemDetails());
    }

    @GetMapping("/shipment/{shipmentId}")
    public ResponseEntity<List<OrderItemResponseDto>> getByShipmentId(@PathVariable Integer shipmentId) {
        return ResponseEntity.ok(orderItemService.getByShipmentId(shipmentId));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemResponseDto>> getByOrderId(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderItemService.getByOrderId(orderId));
    }

}

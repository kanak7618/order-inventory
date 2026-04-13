package com.spring.order_inventory.service;

import com.spring.order_inventory.dto.OrderItemResponseDto;
import java.util.List;

public interface IOrderItemService {

    List<OrderItemResponseDto> getAllOrderItemDetails();

    List<OrderItemResponseDto> getByShipmentId(Integer shipmentId);

    List<OrderItemResponseDto> getByOrderId(Integer orderId);
}
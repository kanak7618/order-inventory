package com.spring.order_inventory;

import com.spring.order_inventory.dto.OrderItemResponseDto;
import com.spring.order_inventory.entity.OrderItem;
import com.spring.order_inventory.repository.OrderItemRepository;
import com.spring.order_inventory.service.impl.OrderItemServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderItemServiceTest {

    @Mock
    private OrderItemRepository repository;

    @InjectMocks
    private OrderItemServiceImpl service;

    private OrderItem item;

    @BeforeEach
    void setup() {
        item = new OrderItem();
        item.setLineItemId(1);
        item.setQuantity(2);
        item.setUnitPrice(100.0);
    }

    // 1. getAllOrderItemDetails()


    @Test
    void getAll_positive() {
        when(repository.findAllOrderItemsRaw())
                .thenReturn(Collections.singletonList(
                        new Object[]{1, 1, 33, "AND", 100.0, 2, null}
                ));

        List<OrderItemResponseDto> result = service.getAllOrderItemDetails();

        assertEquals(1, result.size());
        verify(repository).findAllOrderItemsRaw();
    }

    @Test
    void getAll_empty() {
        when(repository.findAllOrderItemsRaw())
                .thenReturn(Collections.emptyList());

        List<OrderItemResponseDto> result = service.getAllOrderItemDetails();

        assertTrue(result.isEmpty());
    }

    @Test
    void getAll_exception() {
        when(repository.findAllOrderItemsRaw())
                .thenThrow(new RuntimeException("DB Error"));

        assertThrows(RuntimeException.class, () -> {
            service.getAllOrderItemDetails();
        });
    }

    // 2. getByShipmentId()

    @Test
    void getByShipmentId_positive() {
        when(repository.findAllByShipmentRaw(1))
                .thenReturn(Collections.singletonList(
                        new Object[]{1, 1, 33, "AND", 1, 100.0, 2}
                ));

        List<OrderItemResponseDto> result = service.getByShipmentId(1);

        assertEquals(1, result.size());
        verify(repository).findAllByShipmentRaw(1);
    }

    @Test
    void getByShipmentId_empty() {
        when(repository.findAllByShipmentRaw(999))
                .thenReturn(Collections.emptyList());

        List<OrderItemResponseDto> result = service.getByShipmentId(999);

        assertTrue(result.isEmpty());
    }

    @Test
    void getByShipmentId_exception() {
        when(repository.findAllByShipmentRaw(1))
                .thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            service.getByShipmentId(1);
        });
    }

    // 3. getByOrderId()


    @Test
    void getByOrderId_positive() {
        when(repository.findByOrder_OrderId(1))
                .thenReturn(List.of(item));

        List<OrderItemResponseDto> result = service.getByOrderId(1);

        assertEquals(1, result.size());
        verify(repository).findByOrder_OrderId(1);
    }

    @Test
    void getByOrderId_empty() {
        when(repository.findByOrder_OrderId(999))
                .thenReturn(Collections.emptyList());

        List<OrderItemResponseDto> result = service.getByOrderId(999);

        assertTrue(result.isEmpty());
    }

    @Test
    void getByOrderId_exception() {
        when(repository.findByOrder_OrderId(1))
                .thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            service.getByOrderId(1);
        });
    }
}
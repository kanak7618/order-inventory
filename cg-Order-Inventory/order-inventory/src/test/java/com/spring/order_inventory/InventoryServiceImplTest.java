package com.spring.order_inventory;

import com.spring.order_inventory.dto.InventoryDetailsDto;
import com.spring.order_inventory.exception.InvalidFormatException;
import com.spring.order_inventory.repository.InventoryRepository;
import com.spring.order_inventory.service.impl.InventoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceImplTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    private InventoryDetailsDto dto;

    @BeforeEach
    void setUp() {
        dto = new InventoryDetailsDto(
                1, 101, "Store1", 201, "Product1", 50
        );
    }



    //  Positive Test
    @Test
    void testFindByStoreId_Success() {

        when(inventoryRepository.findByStoreId(101))
                .thenReturn(List.of(dto));

        List<InventoryDetailsDto> result =
                inventoryService.findByStoreId(101);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Store1", result.get(0).getStoreName());

        verify(inventoryRepository, times(1))
                .findByStoreId(101);
    }

    //  Negative Test (Invalid storeId)
    @Test
    void testFindByStoreId_InvalidId() {

        assertThrows(InvalidFormatException.class,
                () -> inventoryService.findByStoreId(-1));

        verify(inventoryRepository, never())
                .findByStoreId(any());
    }




    @Test
    void testFindByProductId_Success() {

        when(inventoryRepository.findByProductId(201))
                .thenReturn(List.of(dto));

        List<InventoryDetailsDto> result =
                inventoryService.findByProductId(201);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Product1", result.get(0).getProductName());

        verify(inventoryRepository, times(1))
                .findByProductId(201);
    }

    //  Negative Test (Invalid productId)
    @Test
    void testFindByProductId_InvalidId() {

        assertThrows(InvalidFormatException.class,
                () -> inventoryService.findByProductId(-5));

        verify(inventoryRepository, never())
                .findByProductId(any());
    }
}
package com.spring.order_inventory;

//import com.spring.order_inventory.entity.StoreServiceTest;
import com.spring.order_inventory.constants.OrderStatus;
import com.spring.order_inventory.dto.OrderResponseDto;
import com.spring.order_inventory.dto.StoreResponseDto;
import com.spring.order_inventory.entity.Customer;
import com.spring.order_inventory.entity.Order;
import com.spring.order_inventory.exception.IdNotFoundException;
import com.spring.order_inventory.exception.InvalidFormatException;
import com.spring.order_inventory.repository.OrderRepository;
import com.spring.order_inventory.repository.StoreRepository;
import com.spring.order_inventory.service.impl.StoreServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {
    @Mock
    private StoreRepository storeRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private StoreServiceImpl storeService;


    //POSITIVE TEST CASE
    @Test
    void testgetStoreById(){
        //fake entity
        com.spring.order_inventory.entity.Store mockStore = new com.spring.order_inventory.entity.Store();
        mockStore.setStoreId(1);
        mockStore.setStoreName("Online");
        mockStore.setWebAddress("example.com");
        mockStore.setPhysicalAddress("testing address");
        mockStore.setLatitude(54.32);
        mockStore.setLongitude(44.11);
        mockStore.setLogo(null);
        mockStore.setLogoMimeType(null);
        mockStore.setLogoFilename(null);
        mockStore.setLogoCharset(null);
        mockStore.setLogoLastUpdated(null);

        //fake repo connection
        when(storeRepository.findById(1)).thenReturn(Optional.of(mockStore));

        //fake a result
        StoreResponseDto result = storeService.getStoreById(1);

        assertNotNull(result);
        assertEquals(1,result.getStoreId());
        assertEquals("Online",result.getStoreName());
        assertEquals("testing address", result.getPhysicalAddress());
        assertEquals(54.32,result.getLatitude());
        assertEquals(44.11, result.getLongitude());
        assertEquals(null, result.getLogo());
        assertEquals(null, result.getLogoMimeType());
        assertEquals(null, result.getLogoFilename());
        assertEquals(null, result.getLogoCharset());
        assertEquals(null, result.getLogoLastUpdated());
    }

    //NEGATIVE TEST CASE
    @Test
    void getStoreByIdLessThanZero(){
        assertThrows(InvalidFormatException.class, ()->storeService.getStoreById(-2));
        verifyNoInteractions(storeRepository);
    }

    @Test
    void getStoreById_IdNotFound(){
        when(storeRepository.findById(99)).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class, () -> storeService.getStoreById(99));
    }

    //POSITIVE TEST CASE
    @Test
    void getOrdersByStoreId(){
        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerId(22);
        mockCustomer.setFullName("Alan Turing");

        com.spring.order_inventory.entity.Store mockStore = new com.spring.order_inventory.entity.Store();
        mockStore.setStoreId(12);
        mockStore.setStoreName("TestShop");

        Order order = new Order();
        order.setOrderId(1);
        order.setOrderTms(LocalDateTime.of(2024, 3, 15, 10, 30, 0));
        order.setOrderStatus(OrderStatus.COMPLETE);
        order.setCustomer(mockCustomer);
        order.setStore(mockStore);

        //fake repo connection
        when(orderRepository.findByStoreStoreId(12)).thenReturn(List.of(order));
        //fake result
        List<OrderResponseDto> result = storeService.getOrdersByStoreId(12);

        assertNotNull(result);
        assertEquals(1, result.get(0).getOrderId());
        assertEquals(LocalDateTime.of(2024, 3, 15, 10, 30, 0),result.get(0).getOrderTms());
        assertEquals(OrderStatus.COMPLETE, result.get(0).getOrderStatus());
        assertEquals(22, result.get(0).getCustomerId());
        assertEquals("Alan Turing",result.get(0).getCustomerName());
        assertEquals(12,result.get(0).getStoreId());
        assertEquals("TestShop",result.get(0).getStoreName());
    }

    //NEGATIVE TEST CASE
    @Test
    void getOrdersByStoreIdNegative(){
        assertThrows(InvalidFormatException.class,()->storeService.getOrdersByStoreId(-12));
    }

}

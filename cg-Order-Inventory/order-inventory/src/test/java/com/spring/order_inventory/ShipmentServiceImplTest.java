package com.spring.order_inventory;

import com.spring.order_inventory.dto.ShipmentCustomerDto;
import com.spring.order_inventory.dto.ShipmentResponseDto;
import com.spring.order_inventory.entity.Customer;
import com.spring.order_inventory.entity.Shipment;
import com.spring.order_inventory.entity.Store;
import com.spring.order_inventory.repository.ShipmentRepository;
import com.spring.order_inventory.service.impl.ShipmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.ExpectedCount.times;

@ExtendWith(MockitoExtension.class)
public class ShipmentServiceImplTest {

    @Mock
    private ShipmentRepository shipmentRepository;

    @InjectMocks
    private ShipmentServiceImpl shipmentService;


    @Test
    void testGetShipmentById_Success() {
        Integer id = 1;

        // Create Shipment
        Shipment shipment = new Shipment();
        shipment.setShipmentId(id);

        //ADD CUSTOMER
        Customer customer = new Customer();
        customer.setCustomerId(100);
        shipment.setCustomer(customer);

        Store store = new Store();
        store.setStoreId(10);
        shipment.setStore(store);

        // Mock repository
        when(shipmentRepository.findShipmentWithDetails(id))
                .thenReturn(Optional.of(shipment));

        ShipmentResponseDto result = shipmentService.getShipmentById(id);

        assertNotNull(result);
        assertEquals(id, result.getShipmentId());

        verify(shipmentRepository).findShipmentWithDetails(id);
    }

    //Test getShipmentById NOT FOUND
    @Test
    void testGetShipmentById_NotFound() {

        when(shipmentRepository.findShipmentWithDetails(1))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> shipmentService.getShipmentById(1));
    }

    @Test
    void testGetShipmentsByCustomerSuccess() {

        // Create objects
        Shipment shipment = new Shipment();
        shipment.setShipmentId(1);


        Store store = new Store();
        store.setStoreId(101);
        shipment.setStore(store);


        Customer customer = new Customer();
        customer.setCustomerId(1);
        shipment.setCustomer(customer);

        when(shipmentRepository.findByCustomerId(1))
                .thenReturn(List.of(shipment));

        List<ShipmentCustomerDto> result =
                shipmentService.getShipmentsByCustomer(1);

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getShipmentId());
    }

    @Test
    void testGetShipmentsByCustomer_NoData() {
        Integer customerId = 1;

        when(shipmentRepository.findByCustomerId(customerId))
                .thenReturn(List.of()); // empty list

        List<ShipmentCustomerDto> result =
                shipmentService.getShipmentsByCustomer(customerId);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(shipmentRepository, times(1)).findByCustomerId(customerId);
    }
}
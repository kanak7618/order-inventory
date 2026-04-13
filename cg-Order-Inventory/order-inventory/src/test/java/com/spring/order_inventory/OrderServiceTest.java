package com.spring.order_inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.spring.order_inventory.constants.OrderStatus;
import com.spring.order_inventory.dto.OrderResponseDto;
import com.spring.order_inventory.dto.StoreResponseDto;
import com.spring.order_inventory.entity.Customer;
import com.spring.order_inventory.entity.Order;
import com.spring.order_inventory.entity.Store;
import com.spring.order_inventory.exception.IdNotFoundException;
import com.spring.order_inventory.repository.OrderRepository;
import com.spring.order_inventory.service.IOrderService;
import com.spring.order_inventory.service.impl.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

	@Mock
	private OrderRepository orderRepository;
	
	@InjectMocks
	private OrderServiceImpl orderService;
	
	private Order createMockOrder() {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setFullName("John Doe");
		
		Store store = new Store();
		store.setStoreId(1);
		store.setStoreName("Reliance");
		
		Order order = new Order();
		order.setOrderId(1);
		order.setOrderTms(LocalDateTime.now());
		order.setOrderStatus(OrderStatus.DELIVERED);
		order.setCustomer(customer);
		order.setStore(store);
		
		return order;
	}
	
	@Test
	void testGetOrderById_success() {
		Order order = createMockOrder();
		order.setOrderId(1);
		
		when(orderRepository.findById(1)).thenReturn(Optional.of(order));
		
		OrderResponseDto result = orderService.getOrderById(1);
		assertNotNull(result);
		assertEquals(1, result.getOrderId());
		assertEquals("John Doe", result.getCustomerName());
		assertEquals("Reliance", result.getStoreName());
		
		verify(orderRepository).findById(1);
	}
	
	@Test
	void testGetOrderById_NotFound() {
		when(orderRepository.findById(1)).thenReturn(Optional.empty());
		
		assertThrows(IdNotFoundException.class, () -> orderService.getOrderById(1));
		
	}
	
	@Test
	void testGetStoreByOrderId() {
		Order order = createMockOrder();
		
		when(orderRepository.findById(1)).thenReturn(Optional.of(order));
		
		StoreResponseDto result = orderService.getStoreByOrderId(1);
		
		assertEquals(1, result.getStoreId());
		assertEquals("Reliance", result.getStoreName());
		
	}
	
	@Test
	void testGetOrdersByCustomerId() {
		Order order1 = createMockOrder();
	    order1.setOrderId(1);

	    Order order2 = createMockOrder();
	    order2.setOrderId(2);

	    when(orderRepository.findByCustomerCustomerId(1))
	            .thenReturn(List.of(order1, order2));		
	    
		List<OrderResponseDto> result = orderService.getOrdersByCustomerId(1);
		
		assertEquals(2, result.size());
		assertEquals(1, result.get(0).getOrderId());
	    assertEquals(2, result.get(1).getOrderId());
	    
		verify(orderRepository).findByCustomerCustomerId(1);
	}
	
	@Test
	void testGetOrdersPageByCustomerId() {
	    Order order = createMockOrder();

	    Page<Order> mockPage = new PageImpl<>(List.of(order));

	    when(orderRepository.findOrdersPageByCustomerId(
	            org.mockito.ArgumentMatchers.eq(1),
	            org.mockito.ArgumentMatchers.any()
	    )).thenReturn(mockPage);

	    Page<OrderResponseDto> result =
	        orderService.getOrdersPageByCustomerId(1, 1, 10);

	    assertEquals(1, result.getContent().size());
	    verify(orderRepository)
	        .findOrdersPageByCustomerId(
	            org.mockito.ArgumentMatchers.eq(1),
	            org.mockito.ArgumentMatchers.any()
	        );
	}
//	@Test
//	void getAll_multipleRecords_positive() {
//		when(repository.findAllOrderItemsRaw())
//				.thenReturn(Arrays.asList(
//						new Object[]{1, 1, 33, "AND", 100.0, 2, null},
//						new Object[]{2, 1, 44, "OR", 200.0, 1, null}
//				));
//
//		List<OrderItemResponseDto> result = service.getAllOrderItemDetails();
//
//		assertEquals(2, result.size());
//		verify(repository, times(1)).findAllOrderItemsRaw();
//	}
//	@Test
//	void getAll_nullResponse_negative() {
//		when(repository.findAllOrderItemsRaw())
//				.thenReturn(null);
//
//		List<OrderItemResponseDto> result = service.getAllOrderItemDetails();
//
//		assertTrue(result == null || result.isEmpty());
//	}
	@Test
	void testGetOrdersByStoreId() {
		Order order = createMockOrder();
	    order.setOrderId(1);

	    when(orderRepository.findByStoreStoreId(1))
	            .thenReturn(List.of(order));		
		
		List<OrderResponseDto> result = orderService.getOrdersByStoreId(1);
		
		assertEquals(1, result.size());
		OrderResponseDto dto = result.get(0);

	    assertEquals(1, dto.getOrderId());
	    assertEquals("Reliance", dto.getStoreName());

		verify(orderRepository).findByStoreStoreId(1);
	}
	
}


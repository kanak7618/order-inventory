package com.spring.order_inventory.repository;

import com.spring.order_inventory.entity.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Query("""
			SELECT DISTINCT o FROM Order o
			LEFT JOIN FETCH o.items oi
			LEFT JOIN FETCH oi.product
			LEFT JOIN FETCH oi.shipment
			LEFT JOIN FETCH o.store
			WHERE o.customer.customerId = :customerId
			""")
    List<Order> findByCustomerCustomerId(@Param("customerId") Integer id);
	
	@Query("""
			SELECT o FROM Order o
			WHERE o.customer.customerId = :customerId
			""")
	Page<Order> findOrdersPageByCustomerId(
	        @Param("customerId") Integer customerId,
	        Pageable pageable
	);
	
    List<Order> findByStoreStoreId(Integer id);
}

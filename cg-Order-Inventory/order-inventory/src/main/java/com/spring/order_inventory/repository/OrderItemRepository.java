package com.spring.order_inventory.repository;

import com.spring.order_inventory.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    @Query(value = """
    SELECT 
        oi.line_item_id,
        oi.order_id,
        oi.product_id,
        p.product_name,
        oi.unit_price,
        oi.quantity,
        oi.shipment_id
    FROM order_items oi
    JOIN products p ON oi.product_id = p.product_id
""", nativeQuery = true)
    List<Object[]> findAllOrderItemsRaw();

    List<OrderItem> findByOrderOrderId(Integer id);


    @Query(value = """
    SELECT 
        oi.line_item_id,
        oi.order_id,
        oi.product_id,
        p.product_name,
        oi.shipment_id,
        oi.unit_price,
        oi.quantity
    FROM order_items oi
    JOIN products p ON oi.product_id = p.product_id
    WHERE oi.shipment_id = :shipmentId
""", nativeQuery = true)
    List<Object[]> findAllByShipmentRaw(@Param("shipmentId") Integer shipmentId);
    List<OrderItem> findByShipment_ShipmentId(Integer shipmentId);

    List<OrderItem> findByOrder_OrderId(Integer orderId);
}
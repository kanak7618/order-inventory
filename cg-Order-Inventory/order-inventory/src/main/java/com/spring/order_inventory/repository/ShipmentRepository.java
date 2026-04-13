package com.spring.order_inventory.repository;

import com.spring.order_inventory.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

    //Fetch everything in ONE query
    @Query("SELECT s FROM Shipment s " +
            "JOIN FETCH s.customer " +
            "JOIN FETCH s.store " +
            "WHERE s.shipmentId = :id")
    Optional<Shipment> findShipmentWithDetails(@Param("id") Integer id);


    //Shipments by customer
    @Query("SELECT s FROM Shipment s " +
            "JOIN FETCH s.store " +
            "WHERE s.customer.customerId = :customerId")
    List<Shipment> findByCustomerId(@Param("customerId") Integer customerId);

}
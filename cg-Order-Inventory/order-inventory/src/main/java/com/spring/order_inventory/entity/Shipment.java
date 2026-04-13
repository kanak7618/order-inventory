package com.spring.order_inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
//@Table(name = "shipment")
@Data
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id")
    private Integer shipmentId;

    //Many shipments → one store
    @ManyToOne()
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    //Many shipments → one customer
    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "shipment_status")
    private String shipmentStatus;

    //NEW: Shipment → Order_Items
    @OneToMany(mappedBy = "shipment")
    @JsonIgnore
    private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

}
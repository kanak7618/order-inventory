package com.spring.order_inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "stores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {

    @Id
    @Column(name = "store_id")
    private Integer storeId;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "web_address")
    private String webAddress;

    @Column(name = "physical_address")
    private String physicalAddress;

    private Double latitude;
    private Double longitude;

    @Lob
    @Column(name="logo")
    private byte[] logo;

    @Column(name = "logo_mime_type")
    private String logoMimeType;

    @Column(name = "logo_filename")
    private String logoFilename;

    @Column(name = "logo_charset")
    private String logoCharset;

    @Column(name = "logo_last_updated")
    private LocalDateTime logoLastUpdated;



    // Store -> Orders
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders;

    // Store -> Shipments
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Shipment> shipments;

    // Store -> Inventory
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Inventory> inventories;
}
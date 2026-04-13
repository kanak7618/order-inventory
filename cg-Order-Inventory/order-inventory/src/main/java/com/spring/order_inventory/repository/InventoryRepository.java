package com.spring.order_inventory.repository;
import com.spring.order_inventory.dto.InventoryDetailsDto;
import com.spring.order_inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query("SELECT new com.spring.order_inventory.dto.InventoryDetailsDto(" +
            "i.inventoryId, s.storeId, s.storeName, p.productId, p.productName, i.quantity) " +
            "FROM Inventory i " +
            "JOIN i.product p " +
            "JOIN i.store s " +
            "WHERE s.storeId = :storeId")
    List<InventoryDetailsDto> findByStoreId(Integer storeId);


    @Query("SELECT new com.spring.order_inventory.dto.InventoryDetailsDto(" +
            "i.inventoryId, s.storeId, s.storeName, p.productId, p.productName, i.quantity) " +
            "FROM Inventory i " +
            "JOIN i.product p " +
            "JOIN i.store s " +
            "WHERE p.productId = :productId")
    List<InventoryDetailsDto> findByProductId(Integer productId);

}
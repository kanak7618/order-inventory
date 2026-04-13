package com.spring.order_inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDetailsDto {
    private Integer inventoryId;
    private Integer storeId;
    private String storeName;
    private Integer productId;
    private String productName;
    private int quantity;

}

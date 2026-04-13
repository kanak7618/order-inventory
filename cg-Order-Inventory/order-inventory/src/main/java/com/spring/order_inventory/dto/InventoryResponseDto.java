package com.spring.order_inventory.dto;

import com.spring.order_inventory.entity.Product;
import com.spring.order_inventory.entity.Store;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponseDto {
    private Integer inventoryId;
    private Store store;
    private Product product;
    private Integer quantity;

}

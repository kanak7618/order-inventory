package com.spring.order_inventory.dto;

import java.math.BigDecimal;
import java.util.List;

import com.spring.order_inventory.entity.Inventory;
import com.spring.order_inventory.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {

    private Integer productId;
    private String productName;
    private BigDecimal unitPrice;
    private String colour;
    private String brand;
    private String size;
    private int rating;

}
package com.spring.order_inventory.mapper;

import com.spring.order_inventory.dto.ProductResponseDto;
import com.spring.order_inventory.entity.Product;

public class ProductMapper {
    public static ProductResponseDto productToDto(Product product){
        return new ProductResponseDto(product.getProductId(), product.getProductName(),product.getUnitPrice(),
                product.getColour(), product.getBrand(), product.getSize(), product.getRating());
    }
}

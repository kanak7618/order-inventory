package com.spring.order_inventory.service;

import com.spring.order_inventory.dto.ProductResponseDto;
import java.util.List;


public interface IProductService {

    ProductResponseDto getProductById(Integer id);

    List<ProductResponseDto> getProductsByBrand(String brand);
}
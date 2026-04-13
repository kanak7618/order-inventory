package com.spring.order_inventory.service.impl;

import com.spring.order_inventory.dto.ProductResponseDto;
import com.spring.order_inventory.entity.Product;
import com.spring.order_inventory.exception.IdNotFoundException;
import com.spring.order_inventory.exception.InvalidFormatException;
import com.spring.order_inventory.repository.ProductRepository;
import com.spring.order_inventory.service.IProductService;
import com.spring.order_inventory.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
   private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto getProductById(Integer id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Product not found with id: " + id));
        return ProductMapper.productToDto(product);
    }

    @Override
    public List<ProductResponseDto> getProductsByBrand(String brand) {
        if(brand == null) throw new InvalidFormatException("brand is null ");
        List<Product> products = productRepository.findByBrand(brand);

        return products.stream()
                .map(ProductMapper::productToDto)
                .toList();
    }
}

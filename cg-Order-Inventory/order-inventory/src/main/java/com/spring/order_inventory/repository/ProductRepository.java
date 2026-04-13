package com.spring.order_inventory.repository;

import com.spring.order_inventory.dto.ProductResponseDto;
import com.spring.order_inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByBrand(String brand);
    Product findByProductId(Integer id);
}
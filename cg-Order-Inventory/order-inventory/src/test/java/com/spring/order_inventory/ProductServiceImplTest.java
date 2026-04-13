package com.spring.order_inventory;

import com.spring.order_inventory.dto.ProductResponseDto;
import com.spring.order_inventory.entity.Product;
import com.spring.order_inventory.exception.IdNotFoundException;
import com.spring.order_inventory.exception.InvalidFormatException;
import com.spring.order_inventory.repository.ProductRepository;
import com.spring.order_inventory.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId(1);
        product.setProductName("Laptop");
        product.setBrand("Dell");
        product.setUnitPrice(BigDecimal.valueOf(50000));
        product.setColour("Black");
        product.setSize("Medium");
        product.setRating(4); // int
    }



    //  Positive Test
    @Test
    void testGetProductById_Success() {

        when(productRepository.findById(1))
                .thenReturn(Optional.of(product));

        ProductResponseDto result = productService.getProductById(1);

        assertNotNull(result);
        assertEquals(1, result.getProductId());
        assertEquals("Laptop", result.getProductName());
        assertEquals("Dell", result.getBrand());
        assertEquals(BigDecimal.valueOf(50000), result.getUnitPrice());
        assertEquals(4, result.getRating());

        verify(productRepository, times(1)).findById(1);
    }

    //  Negative Test (Product Not Found)
    @Test
    void testGetProductById_NotFound() {

        when(productRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class,
                () -> productService.getProductById(1));

        verify(productRepository, times(1)).findById(1);
    }



    // Positive Test
    @Test
    void testGetProductsByBrand_Success() {

        when(productRepository.findByBrand("Dell"))
                .thenReturn(List.of(product));

        List<ProductResponseDto> result =
                productService.getProductsByBrand("Dell");

        assertNotNull(result);
        assertEquals(1, result.size());

        ProductResponseDto dto = result.get(0);

        assertEquals("Laptop", dto.getProductName());
        assertEquals("Dell", dto.getBrand());
        assertEquals(BigDecimal.valueOf(50000), dto.getUnitPrice());
        assertEquals(4, dto.getRating());

        verify(productRepository, times(1)).findByBrand("Dell");
    }

    //  Negative Test (Brand Null)
    @Test
    void testGetProductsByBrand_NullBrand() {

        assertThrows(InvalidFormatException.class,
                () -> productService.getProductsByBrand(null));

        verify(productRepository, never()).findByBrand(any());
    }
}
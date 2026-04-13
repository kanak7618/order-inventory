package com.spring.order_inventory.controller;

import com.spring.order_inventory.dto.ProductResponseDto;
import com.spring.order_inventory.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

  private final  IProductService iProductService;
    @Autowired
    public ProductController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

   @GetMapping("/product/{productId}")
    public ResponseEntity<ProductResponseDto> getProductById( @PathVariable Integer productId){
    return new ResponseEntity(iProductService.getProductById(productId),HttpStatus.OK);
    }


    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByBrand( @PathVariable String brand) {
        return new ResponseEntity(iProductService.getProductsByBrand(brand),HttpStatus.OK);
    }

}

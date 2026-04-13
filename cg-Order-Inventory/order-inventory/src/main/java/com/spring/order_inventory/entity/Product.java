package com.spring.order_inventory.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
    private Integer productId;
	
	@Column(name = "product_name", nullable = false)
    private String productName;
	
	@Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;
	
	@Column(name = "colour", nullable = false, length = 45)
    private String colour;

    @Column(name = "brand", nullable = false, length = 45)
    private String brand;

    @Column(name = "size", nullable = false, length = 10)
    private String size;

    @Column(name = "rating")
    private Integer rating;

}

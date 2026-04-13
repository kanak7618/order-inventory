package com.spring.order_inventory.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponseDto {

    private Integer customerId;
    private String fullName;
    private String email;
}
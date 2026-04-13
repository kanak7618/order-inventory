package com.spring.order_inventory.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class StoreResponseDto {
    private Integer storeId;
    private String storeName;
    private String webAddress;
    private String physicalAddress;
    private Double latitude;
    private Double longitude;
    private String logo;
    private String logoMimeType;
    private String logoFilename;
    private String logoCharset;
    private LocalDateTime logoLastUpdated;
}
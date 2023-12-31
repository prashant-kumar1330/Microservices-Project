package com.microservices.inventoryService.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class InventryResponse {

    private String SkuCodes;
    private Boolean isInStock;


}

package com.microservices.inventoryService.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class InventoryDto {

    private String skuCode;
    private BigDecimal price;
    private int quantity;
}

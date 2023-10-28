package com.microservices.inventoryService.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class InventoryDto {

    private String skuCode;
    private BigDecimal price;
    private int quantity;

    public static InventoryDtoBuilder builder() {
        return new InventoryDtoBuilder();
    }

    public static class InventoryDtoBuilder {
        private String skuCode;
        private BigDecimal price;
        private int quantity;

        InventoryDtoBuilder() {
        }

        public InventoryDtoBuilder skuCode(String skuCode) {
            this.skuCode = skuCode;
            return this;
        }

        public InventoryDtoBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public InventoryDtoBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public InventoryDto build() {
            return new InventoryDto(this.skuCode, this.price, this.quantity);
        }

        public String toString() {
            return "InventoryDto.InventoryDtoBuilder(skuCode=" + this.skuCode + ", price=" + this.price + ", quantity=" + this.quantity + ")";
        }
    }
}

package com.microservices.inventoryService.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "sku_code")
    private String skuCode;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "quantity")
    private int quantity;
}

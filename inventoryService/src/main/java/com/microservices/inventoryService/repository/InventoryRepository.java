package com.microservices.inventoryService.repository;

import com.microservices.inventoryService.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
   Inventory findByskuCode(String sku_code);
}

package com.microservices.inventoryService.repository;

import com.microservices.inventoryService.model.Inventory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface InventoryRepository extends JpaRepository<Inventory,Long> {
   List<Inventory> findByskuCodeIn(List<String> sku_code);
   Inventory findByskuCode (String sku_code);


   @Transactional
   @Modifying
   @Query("update Inventory i set i.quantity =quantity+1 where i.id=?1")
   void updateInventory(int id);



}

package com.microservices.inventoryService.repository;

import com.microservices.inventoryService.model.Inventory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface InventoryRepository extends JpaRepository<Inventory,Long> {
   List<Inventory> findByskuCodeIn(List<String> sku_code);
   Optional<Inventory> findByskuCode (String sku_code);


   @Transactional
   @Modifying
   @Query("update inventory set quantity =quantity+1 where id=?1")

   int updateInventory(Long id);



}

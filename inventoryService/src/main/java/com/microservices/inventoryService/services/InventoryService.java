package com.microservices.inventoryService.services;

import com.microservices.inventoryService.Dto.InventoryDto;
import com.microservices.inventoryService.model.Inventory;
import com.microservices.inventoryService.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

  @Autowired
    private final InventoryRepository inventoryRepository;



    public InventoryDto findBySkuCode(String skuCode) {
        Inventory inventory = inventoryRepository.findByskuCode(skuCode);
        return  InventoryDto.builder().skuCode(inventory.getSkuCode())
                .price(inventory.getPrice())
                .quantity(inventory.getQuantity())
                .build();
    }
}

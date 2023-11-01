package com.microservices.inventoryService.services;

import com.microservices.inventoryService.Dto.InventoryDto;
import com.microservices.inventoryService.Dto.InventryResponse;
import com.microservices.inventoryService.model.Inventory;
import com.microservices.inventoryService.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class InventoryService {

  @Autowired
    private final InventoryRepository inventoryRepository;


/*
* cases needs to be handled
* 1) where skucode is not present in table
* 2) where quantity requested is not available in inventory table
* 3) decrease the quantity when order is placed
* */
    public List<InventryResponse> findBySkuCode(List<String> skuCodes) {
        List<Inventory> inventory = inventoryRepository.findByskuCodeIn(skuCodes);
        return inventory.stream().map(item->{
                                return InventryResponse.builder().SkuCodes(item.getSkuCode())
                                        .isInStock(item.getQuantity()>0).build();

                        }).toList();
    }
}

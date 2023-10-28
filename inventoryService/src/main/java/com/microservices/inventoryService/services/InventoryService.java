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



    public List<InventryResponse> findBySkuCode(List<String> skuCodes) {
        List<Inventory> inventory = inventoryRepository.findByskuCodeIn(skuCodes);
        List<InventryResponse>  inventryResponses= skuCodes.stream().map(skuCode->{
                                   Boolean isProductAvailable=false;
                                   if(inventory.indexOf(skuCode)>=0 && inventory.get(inventory.indexOf(skuCode)).getQuantity()>0){
                                       isProductAvailable=true;
                                   }
                             return     InventryResponse.builder().SkuCodes(skuCode)
                                     .isInStock(isProductAvailable).build();

                        }).toList();
 return  inventryResponses ;
    }
}

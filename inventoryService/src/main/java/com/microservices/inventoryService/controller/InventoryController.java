package com.microservices.inventoryService.controller;


import com.microservices.inventoryService.Dto.InventoryDto;
import com.microservices.inventoryService.Dto.InventryResponse;
import com.microservices.inventoryService.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")

public class InventoryController {

   @Autowired
    private  InventoryService inventoryService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    //http://localhost:8083/api/inventory?skuCodes=iphone12&skuCodes=iphone13
   public List<InventryResponse> getProduct(@RequestParam List<String> skuCodes){
         return inventoryService.findBySkuCode(skuCodes);
    }

}

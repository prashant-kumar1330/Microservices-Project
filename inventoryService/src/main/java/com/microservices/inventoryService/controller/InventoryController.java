package com.microservices.inventoryService.controller;


import com.microservices.inventoryService.Dto.InventoryDto;
import com.microservices.inventoryService.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")

public class InventoryController {

   @Autowired
    private  InventoryService inventoryService;

//    public InventoryController(InventoryService inventoryService) {
//        this.inventoryService = inventoryService;
//    }


    @GetMapping("/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
   public  InventoryDto  getProduct(@PathVariable String skuCode){
         return inventoryService.findBySkuCode(skuCode);
    }

}

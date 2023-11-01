package com.microservices.inventoryService;

import com.microservices.inventoryService.model.Inventory;
import com.microservices.inventoryService.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}



	@Bean
	public CommandLineRunner runner(InventoryRepository inventoryRepository){

		return run ->{
			Inventory item1 = inventoryRepository.findByskuCode("iphone12");
			if(item1!=null){

				inventoryRepository.updateInventory((int) item1.getId());
			}
			else{
				Inventory inventory = Inventory.builder()
						.skuCode("iphone12")
						.price(BigDecimal.valueOf(120000))
						.quantity(3)
						.build();
				inventoryRepository.save(inventory);
			}
			Inventory item2 = inventoryRepository.findByskuCode("iphone13");
			if(item2!=null){

				inventoryRepository.updateInventory((int) item2.getId());
			}
			else{
				Inventory inventory1 = Inventory.builder()
						.skuCode("iphone13")
						.price(BigDecimal.valueOf(130000))
						.quantity(4)
						.build();

				inventoryRepository.save(inventory1);
			}


		};

	}
}

package com.microservices.inventoryService;

import com.microservices.inventoryService.model.Inventory;
import com.microservices.inventoryService.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}



	@Bean
	public CommandLineRunner runner(InventoryRepository inventoryRepository){

		return run ->{
			Inventory inventory = Inventory.builder()
					.skuCode("iphone12")
					.price(BigDecimal.valueOf(120000))
					.quantity(3)
					.build();
			Inventory inventory1 = Inventory.builder()
					.skuCode("iphone13")
					.price(BigDecimal.valueOf(130000))
					.quantity(4)
					.build();
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);

		};

	}
}

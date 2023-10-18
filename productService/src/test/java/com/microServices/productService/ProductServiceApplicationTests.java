package com.microServices.productService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microServices.productService.Repository.productRepository;
import com.microServices.productService.dto.ProductRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.math.BigDecimal;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	//create mongo container for test
//	@Container
//	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
//
//
//	//provide connection details to your mongodb container
//	@DynamicPropertySource
//	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
//		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//	}
//    static{
//		mongoDBContainer.start();
//  }
//	@Container
//	public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest")
//			.withExposedPorts(27017);
//
//	static {
//		mongoDBContainer.start();
//		var mappedPort = mongoDBContainer.getMappedPort(27017);
//		System.setProperty("mongodb.container.port", String.valueOf(mappedPort));
//	}

	//mock mvc will call your rest end point for testing
	@Autowired
	private MockMvc mockMvc;

	//object mapper will convert your product object to string type
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private productRepository productRepoistory;



	@Test
	void shouldCreateProduct() throws Exception {

		ProductRequest productRequest = getProductRequest();
		String productRequestString= objectMapper.writeValueAsString(productRequest);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
						.contentType(MediaType.APPLICATION_JSON)
						.content(productRequestString))
				.andExpect(status().isCreated());
		Assertions.assertEquals(1,productRepoistory.findAll().size());
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder().name("iphone15").id(1).description("black 128gb variant").price(BigDecimal.valueOf(80000)).build();
	}


	@Test
	void shouldGetProduct () throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/product").
						accept(MediaType.APPLICATION_JSON)).
				andExpect(status().isOk());

		// Pending  => add some more expect methods to test is properly.



	}

}

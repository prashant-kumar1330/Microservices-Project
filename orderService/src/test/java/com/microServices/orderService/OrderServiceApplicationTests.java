package com.microServices.orderService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microServices.orderService.Repository.OrderRepository;
import com.microServices.orderService.dto.OrderDto;
import com.microServices.orderService.dto.OrderLineItemDto;
import com.microServices.orderService.models.OrderLineItem;
import com.microServices.orderService.models.Orders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private OrderRepository orderRepository;


	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldPlaceOrder() throws Exception {
       OrderDto order = getOrderDto();
	   String orderRequestString = objectMapper.writeValueAsString(order);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/order")
				.contentType(MediaType.APPLICATION_JSON)
				.content(orderRequestString))
				.andExpect(status().isCreated());
		Assertions.assertEquals(1,orderRepository.findAll().size());
	}

	private OrderDto getOrderDto() {
		OrderDto orderDto = new OrderDto();
		OrderLineItemDto orderLineItemDto = new OrderLineItemDto();
		orderLineItemDto.setSkuCode("iphone12");
		orderLineItemDto.setPrice(BigDecimal.valueOf(120000));
		orderLineItemDto.setQuantity(2);
		List<OrderLineItemDto> orderLineItemDtoList =new ArrayList<>();

		orderLineItemDtoList.add(orderLineItemDto);
		orderDto.setOrderLineItemDtoList(orderLineItemDtoList);
		
		return orderDto;


	}

}

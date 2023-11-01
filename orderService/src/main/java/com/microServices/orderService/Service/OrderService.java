package com.microServices.orderService.Service;

import com.microServices.orderService.Repository.OrderRepository;
import com.microServices.orderService.dto.InventoryResponseDto;
import com.microServices.orderService.dto.OrderDto;
import com.microServices.orderService.dto.OrderLineItemDto;
import com.microServices.orderService.models.OrderLineItem;

import com.microServices.orderService.models.Orders;
import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor

public class OrderService {

    private final WebClient.Builder webClientBuilder;
    private final OrderRepository orderRepository;


    public String placeOrder(OrderDto orderDto) {
        Orders orders = new Orders();
        String uuid = UUID.randomUUID().toString();

        orders.setOrderNumber(uuid);

      List<OrderLineItem> orderLineItems= orderDto.getOrderLineItemDtoList().stream().map(this::maptoDto).toList();
      orders.setOrderLineItems(orderLineItems);

      //extracting all the skucodes to make a ap call at inventory
        List<String> skuCodes= orderLineItems.stream().map(OrderLineItem::getSku_code).toList();


      //calling inventory service to check if that item is present in stock or not

       /*
       * order service have the local copy of the registory of the inventory service so even if the discovery server is down order service
       * can call the inventory service by finding the url of it in local registory
       * */

       InventoryResponseDto[] inventoryResponse= webClientBuilder.build().get()
               .uri("http://inventory-service/api/inventory",
                       uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build()) //http://localhost:8083/api/inventory?skuCodes=iphone12&skuCodes=iphone13
               .retrieve()
               .bodyToMono(InventoryResponseDto[].class)
               .block(); // by default webclinet make asyncSchronus call to api so to make it sysnchronus we can add block menthod in the end

        boolean response =Arrays.stream(inventoryResponse).allMatch(item-> item.getIsInStock());

        if(response){
            orderRepository.save(orders);
            return "Order Placed";
        }
      return "order can not be placed , item is out of stock";
    }

    private OrderLineItem maptoDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setSku_code(orderLineItemDto.getSkuCode());
        return orderLineItem;
    }
}

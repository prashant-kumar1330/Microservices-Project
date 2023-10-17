package com.microServices.orderService.Service;

import com.microServices.orderService.Repository.OrderRepository;
import com.microServices.orderService.dto.OrderDto;
import com.microServices.orderService.dto.OrderLineItemDto;
import com.microServices.orderService.models.OrderLineItem;

import com.microServices.orderService.models.Orders;
import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor

public class OrderService {
    static Logger logger = LogManager.getLogger();
    private final OrderRepository orderRepository;
    public String placeOrder(OrderDto orderDto) {
        Orders orders = new Orders();
        String uuid = UUID.randomUUID().toString();
        logger.debug(uuid);
        orders.setOrderNumber(uuid);

      List<OrderLineItem> orderLineItems= orderDto.getOrderLineItemDtoList().stream().map(this::maptoDto).toList();
      orders.setOrderLineItems(orderLineItems);
      orderRepository.save(orders);
      return "Order Placed";
    }

    private OrderLineItem maptoDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setSku_code(orderLineItemDto.getSkuCode());
        return orderLineItem;
    }
}

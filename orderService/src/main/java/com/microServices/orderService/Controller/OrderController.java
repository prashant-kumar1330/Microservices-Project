package com.microServices.orderService.Controller;


import brave.Tracer;
import com.microServices.orderService.Service.OrderService;
import com.microServices.orderService.dto.OrderDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

   private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
  @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
   public String postOrder(@RequestBody OrderDto orderDto){
       return   orderService.placeOrder(orderDto);
    }

    public String fallbackMethod(OrderDto orderDto, RuntimeException runtimeException){
        return "oops!! something went wrong!!!";
    }

}

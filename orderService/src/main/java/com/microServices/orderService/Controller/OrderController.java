package com.microServices.orderService.Controller;


import com.microServices.orderService.Service.OrderService;
import com.microServices.orderService.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

   private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
   public String postOrder(@RequestBody OrderDto orderDto){
       return   orderService.placeOrder(orderDto);
    }

}

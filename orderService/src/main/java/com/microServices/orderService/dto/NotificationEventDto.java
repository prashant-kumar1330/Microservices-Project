package com.microServices.orderService.dto;

import com.microServices.orderService.models.OrderLineItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationEventDto {

    private String orderId;

}

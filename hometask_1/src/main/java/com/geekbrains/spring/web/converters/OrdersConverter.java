package com.geekbrains.spring.web.converters;

import com.geekbrains.spring.web.dto.OrderDto;
import com.geekbrains.spring.web.entities.Order;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrdersConverter {

    public OrderDto entityToDto(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .totalPrice(order.getTotalPrice())
                .address(order.getAddress())
                .phone(order.getPhone())
                .build();
    }
}

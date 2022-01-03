package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.dto.OrderDto;
import com.geekbrains.spring.web.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestParam String phone,
                                         @RequestParam String address,
                                         Principal principal) {
        if (principal == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        OrderDto orderDto = ordersService.createOrder(phone,address,principal);
        return new ResponseEntity<>(orderDto,HttpStatus.CREATED);
    }


}

package com.geekbrains.spring.web.core.controllers;

import com.geekbrains.spring.web.api.dto.Cart;
import com.geekbrains.spring.web.api.dto.ProductDto;
import com.geekbrains.spring.web.core.converters.OrderConverter;
import com.geekbrains.spring.web.core.dto.OrderDetailsDto;
import com.geekbrains.spring.web.core.dto.OrderDto;
import com.geekbrains.spring.web.core.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;
    private final RestTemplate restTemplate;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username, @RequestBody OrderDetailsDto orderDetailsDto) {
        String address = "http://cart-service/web-market-carts/api/v1/cart/ms/" + username;
        Cart currentCart = restTemplate.getForObject(address, Cart.class);
        orderService.createOrder(currentCart, username, orderDetailsDto);
        String uriToClearCart = "http://cart-service/web-market-carts/api/v1/cart/ms/%s/clear";
        restTemplate.getForObject(String .format(uriToClearCart, username), Cart.class);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(@RequestHeader String username) {
        return orderService.findOrdersByUsername(username).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}

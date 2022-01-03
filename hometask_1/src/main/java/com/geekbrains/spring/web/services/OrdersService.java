package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.converters.OrdersConverter;
import com.geekbrains.spring.web.dto.OrderDto;
import com.geekbrains.spring.web.dto.OrderItemDto;
import com.geekbrains.spring.web.entities.Order;
import com.geekbrains.spring.web.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final UserService userService;
    private final OrderItemsService orderItemsService;
    private final CartService cartService;

    @Transactional
    public  OrderDto createOrder(String phone, String address, Principal principal) {
        var currentUser = userService.findByUsername(principal.getName()).orElseThrow(()->new RuntimeException("no such user"));
        var currentCart = cartService.getCurrentCart();
        var order = ordersRepository.save(Order.builder()
                .user(currentUser)
                .totalPrice(currentCart.getTotalPrice())
                .address(address)
                .phone(phone)
                .build());
        for (OrderItemDto item : currentCart.getItems()) {
            orderItemsService.createOrderItem(item, order);
        }
        return OrdersConverter.entityToDto(order);
    }
}

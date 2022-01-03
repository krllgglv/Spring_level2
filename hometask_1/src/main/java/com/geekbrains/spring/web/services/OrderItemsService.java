package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.dto.OrderItemDto;
import com.geekbrains.spring.web.entities.Order;
import com.geekbrains.spring.web.entities.OrderItem;
import com.geekbrains.spring.web.repositories.OrderItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemsService {
    private final ProductsService productsService;
    private final OrderItemsRepository orderItemsRepository;

    public void createOrderItem(OrderItemDto item, Order order){
        var product = productsService.findById(item.getProductId()).orElseThrow(()->new RuntimeException("no such product"));
        orderItemsRepository.save(OrderItem.builder()
                        .product(product)
                        .order(order)
                        .quantity(item.getQuantity())
                        .pricePerProduct(item.getPricePerProduct())
                        .price(item.getPrice())
                .build());

    }


}

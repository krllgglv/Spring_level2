package com.geekbrains.spring.web.core.integrations;

import com.geekbrains.spring.web.api.carts.CartDto;
import com.geekbrains.spring.web.api.carts.CartItemDto;
import com.geekbrains.spring.web.api.core.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StatServiceIntegration {

    private final WebClient statServiceWebClient;



    public void addProductsToStatistic(List<CartItemDto> items) {
         statServiceWebClient.post()
                .uri("/api/v1/stat/popular/products/to_orders")
                 .bodyValue(items)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();

    }
}

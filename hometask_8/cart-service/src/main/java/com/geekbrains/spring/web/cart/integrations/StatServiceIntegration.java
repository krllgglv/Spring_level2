package com.geekbrains.spring.web.cart.integrations;

import com.geekbrains.spring.web.api.core.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class StatServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${integrations.stat-service.url}")
    private String statServiceUrl;

    public void addProductToStatistic(ProductDto productDto) {
       restTemplate.postForObject(statServiceUrl + "/api/v1/stat/popular/products/to_carts", productDto,Object.class);
    }
}

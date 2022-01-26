package ru.k.gogolev.front_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.k.gogolev.common.ProductDto;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@EnableEurekaClient
@RequiredArgsConstructor
public class ProductsController {

    private final RestTemplate restTemplate;

    @GetMapping
    public List<ProductDto> getAllProducts(){
        return Arrays.asList(restTemplate.getForEntity("http://products-service/api/v1/products", ProductDto[].class).getBody());
    }
}

package ru.k.gogolev.product_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.k.gogolev.common.ProductDto;
import ru.k.gogolev.product_service.services.ProductService;
import ru.k.gogolev.product_service.util.ProductsUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@EnableEurekaClient
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.findAll().stream().map(ProductsUtil::entityToDto).collect(Collectors.toList());
    }
}

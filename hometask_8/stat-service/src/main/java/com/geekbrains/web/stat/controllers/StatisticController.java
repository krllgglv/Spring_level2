package com.geekbrains.web.stat.controllers;

import com.geekbrains.spring.web.api.carts.CartItemDto;
import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.api.stat.ProductInCartsDto;
import com.geekbrains.spring.web.api.stat.ProductInOrdersDto;
import com.geekbrains.web.stat.services.ProductsInOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.geekbrains.web.stat.services.ProductsInCartsService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stat/popular/products")
@RequiredArgsConstructor
public class StatisticController {

    private final ProductsInCartsService productsInCartsService;
    private final ProductsInOrdersService productsInOrdersService;


    @PostMapping("/to_carts")
    public void addProductFromCartToStatistic(@RequestBody ProductDto productDto) {
        productsInCartsService.save(productDto);

    }

    @PostMapping("/to_orders")
    public void getCart(@RequestBody List<CartItemDto> items) {
        productsInOrdersService.save(items);
    }

    @GetMapping("/in_carts")
    public List<ProductInCartsDto> getFiveMostPopularProductsInCarts(@RequestParam (required = false,defaultValue = "5") Integer quantity,
                                                                     @RequestParam (required = false, defaultValue = "-1") Integer fromDate,
                                                                     @RequestParam (required = false,defaultValue = "30") Integer period){
        if (fromDate == -1){
            fromDate = LocalDate.now().getDayOfYear();
        }
        return productsInCartsService.getFiveMostPopularProductsInCarts(fromDate, period, quantity);
    }

    @GetMapping("/in_orders")
    public List<ProductInOrdersDto> getFiveMostPopularProductsInOrders(@RequestParam (required = false,defaultValue = "5") Integer quantity,
                                                                       @RequestParam (required = false, defaultValue = "-1") Integer fromDate,
                                                                       @RequestParam (required = false,defaultValue = "30") Integer period){
        if (fromDate == -1){
            fromDate = LocalDate.now().getDayOfYear();
        }
        return productsInOrdersService.getMostPopularProductsInOrders(fromDate, period, quantity);
    }
}

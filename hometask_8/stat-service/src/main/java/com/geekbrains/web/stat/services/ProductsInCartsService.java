package com.geekbrains.web.stat.services;

import com.geekbrains.spring.web.api.core.ProductDto;
import com.geekbrains.spring.web.api.stat.ProductInCartsDto;
import com.geekbrains.web.stat.integration.CoreServiceIntegration;
import com.geekbrains.web.stat.model.ProductInCarts;
import com.geekbrains.web.stat.model.projection.ProductsInCartsProjection;
import com.geekbrains.web.stat.repositories.ProductsInCartsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsInCartsService {
    private final ProductsInCartsRepository productsInCartsRepository;
    private final CoreServiceIntegration productsServiceIntegration;


    public List<ProductInCartsDto> getFiveMostPopularProductsInCarts(Integer fromDate, Integer period, Integer quantity){
        Integer toDate = Math.max(fromDate - period, 1);
        List<ProductsInCartsProjection> listOfProjection = productsInCartsRepository.findFiveMostPopularProductsInOrders(fromDate, toDate, PageRequest.of(0, quantity));
        return listOfProjection.stream().map(it ->
                new ProductInCartsDto(productsServiceIntegration.findById(it.getProductId()).get().getTitle(), it.getQuantity())).collect(Collectors.toList());
    }
@Transactional
    public void save(ProductDto productDto) {
        Integer currentYear = LocalDate.now().getYear();
        Integer currentDayOfYear = LocalDate.now().getDayOfYear();
        ProductInCarts currentState = productsInCartsRepository.findByProductIdAndDayOfYearAndYear(productDto.getId(), currentDayOfYear, currentYear);
        if (currentState == null){
            ProductInCarts newProductInCarts = new ProductInCarts();
            newProductInCarts.setProductId(productDto.getId());
            newProductInCarts.setQuantity(1);
            newProductInCarts.setDayOfYear(currentDayOfYear);
            newProductInCarts.setYear(currentYear);
            productsInCartsRepository.save(newProductInCarts);
        } else{
            currentState.setQuantity(currentState.getQuantity()+1);
            productsInCartsRepository.save(currentState);
        }
    }
}

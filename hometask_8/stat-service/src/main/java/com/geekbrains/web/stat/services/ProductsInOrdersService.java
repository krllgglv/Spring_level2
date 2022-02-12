package com.geekbrains.web.stat.services;

import com.geekbrains.spring.web.api.carts.CartItemDto;
import com.geekbrains.spring.web.api.stat.ProductInCartsDto;
import com.geekbrains.spring.web.api.stat.ProductInOrdersDto;
import com.geekbrains.web.stat.integration.CoreServiceIntegration;
import com.geekbrains.web.stat.model.ProductInOrders;
import com.geekbrains.web.stat.model.projection.ProductsInOrdersProjection;
import com.geekbrains.web.stat.repositories.ProductsInOrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsInOrdersService {
    private final ProductsInOrdersRepository productsInOrdersRepository;
    private final CoreServiceIntegration productsServiceIntegration;


    public List<ProductInOrdersDto> getMostPopularProductsInOrders(Integer fromDate, Integer period, Integer quantity) {
        Integer toDate = Math.max(fromDate - period, 1);
        List<ProductsInOrdersProjection> listOfProjection = productsInOrdersRepository.findFiveMostPopularProductsInOrders(fromDate, toDate, PageRequest.of(0, quantity));
        return listOfProjection.stream().map(it ->
                new ProductInOrdersDto(productsServiceIntegration.findById(it.getProductId()).get().getTitle(), it.getQuantity())).collect(Collectors.toList());
    }

    @Transactional
    public void save(List<CartItemDto> items) {
        Integer currentYear = LocalDate.now().getYear();
        Integer currentDayOfYear = LocalDate.now().getDayOfYear();
        for (CartItemDto item : items) {
            ProductInOrders currentState = productsInOrdersRepository.findByProductIdAndDayOfYearAndYear(item.getProductId(), currentDayOfYear, currentYear);
            if (currentState == null) {
                ProductInOrders newProductInOrders = new ProductInOrders();
                newProductInOrders.setProductId(item.getProductId());
                newProductInOrders.setQuantity(1);
                newProductInOrders.setDayOfYear(currentDayOfYear);
                newProductInOrders.setYear(currentYear);
                productsInOrdersRepository.save(newProductInOrders);
            } else {
                currentState.setQuantity(currentState.getQuantity() + item.getQuantity());
                productsInOrdersRepository.save(currentState);
            }
        }
    }

}

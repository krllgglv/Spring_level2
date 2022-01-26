package ru.k.gogolev.product_service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.k.gogolev.common.ProductDto;
import ru.k.gogolev.product_service.entities.ProductEntity;
import ru.k.gogolev.product_service.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }
}

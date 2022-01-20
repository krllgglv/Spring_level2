package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.converters.CategoryConverter;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.entities.CategoryEntity;
import com.geekbrains.spring.web.entities.ProductEntity;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.repositories.ProductsRepository;
import com.geekbrains.spring.web.repositories.specifications.ProductsSpecifications;
import com.geekbrains.spring.web.soap.products.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final CategoriesService categoriesService;

    @Transactional
    public Page<ProductEntity> findAll(Integer minPrice, Integer maxPrice, String partTitle, Integer categoryId, Integer page) {
        Specification<ProductEntity> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.titleLike(partTitle));
        }
        if (categoryId != null) {
            CategoryEntity category = categoriesService.findOneById(categoryId).orElseThrow(() -> new ResourceNotFoundException("No such category"));
            spec = spec.and(ProductsSpecifications.categoryEquals(category));
        }



        return productsRepository.findAll(spec, PageRequest.of(page - 1, 8));
    }

    public Optional<ProductEntity> findById(Long id) {
        return productsRepository.findById(id);
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public ProductEntity save(ProductEntity product) {
        return productsRepository.save(product);
    }

    @Transactional
    public ProductEntity update(ProductDto productDto) {
        ProductEntity product = productsRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить продукта, не надйен в базе, id: " + productDto.getId()));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        return product;
    }

    public Product findByIdSOAP(Long id) {
        return productsRepository.findById(id).map(functionEntityToSoap).orElseThrow(()->new NoSuchElementException("no such product"));
    }

    public List <Product> findAllSOAP() {

        return productsRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }






    public static final Function<ProductEntity, Product> functionEntityToSoap = productEntity -> {
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setTitle(productEntity.getTitle());
        product.setPrice(productEntity.getPrice());
        product.setCategory(CategoryConverter.functionEntityToSoap(productEntity.getCategory()));

        return product;
    };
}

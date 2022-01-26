package ru.k.gogolev.product_service.util;

import lombok.experimental.UtilityClass;
import ru.k.gogolev.common.ProductDto;
import ru.k.gogolev.product_service.entities.ProductEntity;

@UtilityClass
public class ProductsUtil {

    public ProductDto entityToDto(ProductEntity entity){
        return new ProductDto(entity.getId(), entity.getTitle(), entity.getPrice());
    }
}

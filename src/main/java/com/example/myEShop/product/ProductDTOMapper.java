package com.example.myEShop.product;

import java.util.function.Function;
import org.springframework.stereotype.Service;


@Service
public class ProductDTOMapper implements Function<Product, ProductDTO>{

    @Override
    public ProductDTO apply(Product product){
        return new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getImage1(),
                product.getImage2(),
                product.getImage3(),
                product.getImage4(),
                product.getPrice(),
                product.getCurrency());
    }
}

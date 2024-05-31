package com.example.myEShop.product;

import org.springframework.stereotype.Service;

import java.util.function.Function;

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
                product.getImage5(),
                product.getPrice(),
                product.getCurrency());
    }
}

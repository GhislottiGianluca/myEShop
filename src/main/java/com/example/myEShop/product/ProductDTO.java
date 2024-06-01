package com.example.myEShop.product;

public record ProductDTO (
        Long id,
        String title,
        String description,
        String image1,
        String image2,
        String image3,
        String image4,
        double price,
        String currency
){
}

package com.example.myEShop.product;

public record ProductDTO (
        String title,
        String description,
        String image1,
        String image2,
        String image3,
        String image4,
        String image5,
        double price,
        String currency
){
}

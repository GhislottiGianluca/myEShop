package com.example.myEShop.product;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class ProductBuilder {

    private String title;
    private String description;
    private String category;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;
    private double price;
    private String currency;
    private int remained;
    private int sold;

    public ProductBuilder(String title) {
        this.title = title;
    }

    public ProductBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ProductBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder category(ProductCategories category) {
        this.category = category.getValue();
        return this;
    }

    public ProductBuilder image1(String image1) {
        this.image1 = image1;
        return this;
    }

    public ProductBuilder image2(String image2) {
        this.image2 = image2;
        return this;
    }

    public ProductBuilder image3(String image3) {
        this.image3 = image3;
        return this;
    }

    public ProductBuilder image4(String image4) {
        this.image4 = image4;
        return this;
    }

    public ProductBuilder image5(String image5) {
        this.image5 = image5;
        return this;
    }

    public ProductBuilder price(double price) {
        this.price = price;
        return this;
    }

    public ProductBuilder currency(String currency) {
        this.currency = currency;
        return this;
    }

    public ProductBuilder remained(int remained) {
        this.remained = remained;
        return this;
    }

    public ProductBuilder sold(int sold) {
        this.sold = sold;
        return this;
    }

    public Product build() {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setCategory(category);
        product.setImage1(image1);
        product.setImage2(image2);
        product.setImage3(image3);
        product.setImage4(image4);
        product.setImage5(image5);
        product.setPrice(price);
        product.setCurrency(currency);
        product.setRemained(remained);
        product.setSold(sold);
        return product;
    }
}

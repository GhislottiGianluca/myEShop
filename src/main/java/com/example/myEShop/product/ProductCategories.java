package com.example.myEShop.product;

public enum ProductCategories {
    CUP("Cup"),
    TSHIRT("Thirt"),
    SWEATSHIRT("Sweatshirt");

    private final String value;

    ProductCategories(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


package com.example.myEShop.product;

/**
 * Data Transfer Object (DTO) for product information.
 * <p>
 * This record is used to transfer product data between processes,
 * containing details such as the product's ID, title, description,
 * images, price, and currency.
 * </p>
 *
 * @param id the unique identifier for the product
 * @param title the title of the product
 * @param description the description of the product
 * @param image1 the URL of the first image of the product
 * @param image2 the URL of the second image of the product
 * @param image3 the URL of the third image of the product
 * @param image4 the URL of the fourth image of the product
 * @param price the price of the product
 * @param currency the currency of the product's price
 */
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
) {
}

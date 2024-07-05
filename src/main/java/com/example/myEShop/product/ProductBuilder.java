package com.example.myEShop.product;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

/**
 * Builder class for constructing {@link Product} objects.
 * <p>
 * This builder provides a fluent API for creating instances of the {@link Product} class.
 * </p>
 */
public class ProductBuilder {

    private String title;
    private String description;
    private String category;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private double price;
    private String currency;
    private int remained;
    private int sold;

    /**
     * Constructs a new ProductBuilder with the specified title.
     *
     * @param title the title of the product
     */
    public ProductBuilder(String title) {
        this.title = title;
    }

    /**
     * Sets the title of the product.
     *
     * @param title the title of the product
     * @return the current instance of {@link ProductBuilder}
     */
    public ProductBuilder title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets the description of the product.
     *
     * @param description the description of the product
     * @return the current instance of {@link ProductBuilder}
     */
    public ProductBuilder description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the category of the product.
     *
     * @param category the category of the product
     * @return the current instance of {@link ProductBuilder}
     */
    public ProductBuilder category(ProductCategories category) {
        this.category = category.getValue();
        return this;
    }

    /**
     * Sets the first image URL of the product.
     *
     * @param image1 the first image URL of the product
     * @return the current instance of {@link ProductBuilder}
     */
    public ProductBuilder image1(String image1) {
        this.image1 = image1;
        return this;
    }

    /**
     * Sets the second image URL of the product.
     *
     * @param image2 the second image URL of the product
     * @return the current instance of {@link ProductBuilder}
     */
    public ProductBuilder image2(String image2) {
        this.image2 = image2;
        return this;
    }

    /**
     * Sets the third image URL of the product.
     *
     * @param image3 the third image URL of the product
     * @return the current instance of {@link ProductBuilder}
     */
    public ProductBuilder image3(String image3) {
        this.image3 = image3;
        return this;
    }

    /**
     * Sets the fourth image URL of the product.
     *
     * @param image4 the fourth image URL of the product
     * @return the current instance of {@link ProductBuilder}
     */
    public ProductBuilder image4(String image4) {
        this.image4 = image4;
        return this;
    }

    /**
     * Sets the price of the product.
     *
     * @param price the price of the product
     * @return the current instance of {@link ProductBuilder}
     */
    public ProductBuilder price(double price) {
        this.price = price;
        return this;
    }

    /**
     * Sets the currency of the product's price.
     *
     * @param currency the currency of the product's price
     * @return the current instance of {@link ProductBuilder}
     */
    public ProductBuilder currency(String currency) {
        this.currency = currency;
        return this;
    }

    /**
     * Sets the quantity of the product remaining in stock.
     *
     * @param remained the quantity of the product remaining in stock
     * @return the current instance of {@link ProductBuilder}
     */
    public ProductBuilder remained(int remained) {
        this.remained = remained;
        return this;
    }

    /**
     * Sets the quantity of the product sold.
     *
     * @param sold the quantity of the product sold
     * @return the current instance of {@link ProductBuilder}
     */
    public ProductBuilder sold(int sold) {
        this.sold = sold;
        return this;
    }

    /**
     * Builds a new {@link Product} instance with the specified properties.
     *
     * @return a new instance of {@link Product}
     */
    public Product build() {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setCategory(category);
        product.setImage1(image1);
        product.setImage2(image2);
        product.setImage3(image3);
        product.setImage4(image4);
        product.setPrice(price);
        product.setCurrency(currency);
        product.setRemained(remained);
        product.setSold(sold);
        return product;
    }
}

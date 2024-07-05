package com.example.myEShop.product;

/**
 * Enumeration representing the categories of products available in the eShop.
 * <p>
 * This enum defines the various categories that a product can belong to.
 * Each category has a corresponding string value for display purposes.
 * </p>
 */
public enum ProductCategories {
    /**
     * Category for cups.
     */
    CUP("Cup"),

    /**
     * Category for T-shirts.
     */
    TSHIRT("TShirt"),

    /**
     * Category for sweatshirts.
     */
    SWEATSHIRT("Sweatshirt");

    /**
     * The display value of the category.
     */
    private final String value;

    /**
     * Constructs a new ProductCategories enum with the specified display value.
     *
     * @param value the display value of the category
     */
    ProductCategories(String value) {
        this.value = value;
    }

    /**
     * Returns the display value of the category.
     *
     * @return the display value of the category
     */
    public String getValue() {
        return value;
    }
}

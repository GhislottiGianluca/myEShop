package com.example.myEShop.cart;

import com.example.myEShop.product.ProductDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * Wrapper class for items in a shopping cart.
 * <p>
 * This class encapsulates a product and its quantity in the cart.
 * </p>
 */
@Getter
@Setter
public class CartItemsWrapper {

    /**
     * The product in the cart.
     */
    private ProductDTO product;

    /**
     * The quantity of the product in the cart.
     */
    private Integer quantity;

    /**
     * Constructs a new CartItemsWrapper with the specified product and quantity.
     *
     * @param product the product in the cart
     * @param quantity the quantity of the product in the cart
     */
    public CartItemsWrapper(ProductDTO product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}

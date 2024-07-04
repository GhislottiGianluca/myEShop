package com.example.myEShop.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request object for cart operations.
 * <p>
 * This class is used to encapsulate the data sent in requests to
 * add, remove, or update items in the shopping cart.
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {

    /**
     * The ID of the product to be added, removed, or updated in the cart.
     */
    private Long prod_id;

    /**
     * The quantity of the product to be added or updated in the cart.
     */
    private int quantity;
}

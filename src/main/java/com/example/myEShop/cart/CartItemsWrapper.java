package com.example.myEShop.cart;

import com.example.myEShop.product.ProductDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemsWrapper {

    private ProductDTO product;
    private Integer quantity;

    public CartItemsWrapper(ProductDTO product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}

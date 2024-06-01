package com.example.myEShop.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {

    private Long prod_id;
    private int quantity;
}

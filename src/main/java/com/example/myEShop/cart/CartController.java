package com.example.myEShop.cart;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public void handlingAddingCartItems(@RequestBody Long user_id, Long prod_id){
        cartService.addCartElement(user_id, prod_id);
    }

    @PostMapping("/delete")
    public void handlingDeletingCartItems(@RequestBody Long user_id, Long prod_id){
        cartService.deleteCartElement(user_id, prod_id);
    }

}

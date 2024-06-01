package com.example.myEShop.cart;

import com.example.myEShop.appuser.AppUser;
import com.example.myEShop.appuser.AppUserService;
import com.example.myEShop.product.ProductDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="api/v1/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;
    private final AppUserService appUserService;

    @PostMapping("/delete")
    public void handlingDeletingCartItems(@RequestBody Long prod_id){
        cartService.deleteCartElement(prod_id);
    }

    @PostMapping("/addProduct")
    public void handlingAddingCartItems(@RequestBody CartRequest cartRequest) {
        cartService.addCartElement(cartRequest.getProd_id(), cartRequest.getQuantity());
    }

    @PostMapping("/removeProduct")
    public void handlingRemovingCartItem(@RequestBody Long prod_id){
        cartService.removeCartElement(prod_id);
    }

    @PutMapping("/cartItemQuantity")
    public void handlingCartQuantity(@RequestBody Long prod_id, int quantity){
        cartService.handlingCartQuantity(prod_id, quantity);
    }

    @GetMapping("/getCartItems")
    public List<CartItemsWrapper> getCartItems() {
        return cartService.getCartItems();
    }
}

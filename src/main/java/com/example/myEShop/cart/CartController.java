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

    @GetMapping("/getCartItems")
    public List<CartItemsWrapper> getCartItems() {
        return cartService.getCartItems();
    }

    @PostMapping("/addProduct")
    public void handlingAddingCartItems(@RequestBody CartRequest cartRequest) {
        cartService.addCartElement(cartRequest.getProd_id(), cartRequest.getQuantity());
    }

    @PostMapping("/removeCartItem")
    public void handlingRemovingCartItem(@RequestBody CartRequest cartRequest){
        cartService.removeCartElement(cartRequest.getProd_id());
    }

    @PutMapping("/updateItemQuantity")
    public void handlingCartQuantity(@RequestBody CartRequest cartRequest){
        cartService.handlingCartQuantity(cartRequest.getProd_id(), cartRequest.getQuantity());
    }
}

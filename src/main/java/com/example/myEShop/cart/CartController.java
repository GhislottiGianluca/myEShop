package com.example.myEShop.cart;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing shopping carts.
 * <p>
 * This controller provides endpoints for retrieving, adding, removing,
 * and updating items in a shopping cart.
 * </p>
 */
@RestController
@RequestMapping(path="api/v1/cart")
@AllArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * Retrieves the items in the current user's cart.
     *
     * @return a list of {@link CartItemsWrapper} representing the items in the cart
     */
    @GetMapping("/getCartItems")
    public List<CartItemsWrapper> getCartItems() {
        return cartService.getCartItems();
    }

    /**
     * Adds a product to the cart.
     *
     * @param cartRequest the request containing the product ID and quantity to be added
     */
    @PostMapping("/addProduct")
    public void handlingAddingCartItems(@RequestBody CartRequest cartRequest) {
        cartService.addCartElement(cartRequest.getProd_id(), cartRequest.getQuantity());
    }

    /**
     * Removes a product from the cart.
     *
     * @param cartRequest the request containing the product ID to be removed
     */
    @PostMapping("/removeCartItem")
    public void handlingRemovingCartItem(@RequestBody CartRequest cartRequest) {
        cartService.removeCartElement(cartRequest.getProd_id());
    }

    /**
     * Updates the quantity of a product in the cart.
     *
     * @param cartRequest the request containing the product ID and new quantity
     */
    @PutMapping("/updateItemQuantity")
    public void handlingCartQuantity(@RequestBody CartRequest cartRequest) {
        cartService.handlingCartQuantity(cartRequest.getProd_id(), cartRequest.getQuantity());
    }
}

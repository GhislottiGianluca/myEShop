package com.example.myEShop.cart;

import com.example.myEShop.appuser.AppUserService;
import com.example.myEShop.product.ProductDTO;
import com.example.myEShop.product.ProductService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service class for managing shopping carts.
 * <p>
 * This service handles operations such as adding, removing, and updating items
 * in the shopping cart, as well as retrieving cart contents.
 * </p>
 */
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final AppUserService appUserService;
    private final ProductService productService;

    /**
     * Constructs a new CartService with the specified dependencies.
     *
     * @param cartRepository the repository for managing cart entities
     * @param appUserService the service for managing application users
     * @param productService the service for managing products
     */
    public CartService(CartRepository cartRepository,
                       @Lazy AppUserService appUserService,
                       ProductService productService) {
        this.cartRepository = cartRepository;
        this.appUserService = appUserService;
        this.productService = productService;
    }

    /**
     * Saves the specified cart.
     *
     * @param cart the cart to save
     */
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    /**
     * Retrieves the cart associated with the current user.
     *
     * @return the cart of the current user
     */
    public Cart getCartById() {
        return cartRepository.findCartByUserId(appUserService.getCurrentUserId());
    }

    /**
     * Adds a product to the cart or updates the quantity if the product already exists in the cart.
     *
     * @param id_prod the ID of the product to add
     * @param quantity the quantity of the product to add
     */
    @Transactional
    public void addCartElement(Long id_prod, int quantity) {
        Cart cart = getCartById();
        Map<Long, Integer> updated_items = cart.getItems();

        if (updated_items.containsKey(id_prod)) {
            updated_items.merge(id_prod, quantity, Integer::sum);
        } else {
            updated_items.put(id_prod, quantity);
        }

        cart.setItems(updated_items);
        cartRepository.save(cart);
    }

    /**
     * Removes all items from the cart.
     */
    @Transactional
    public void removeAllCartElement() {
        Cart cart = getCartById();
        cart.setItems(new HashMap<>());
        cartRepository.save(cart);
    }

    /**
     * Removes a specific product from the cart.
     *
     * @param id_prod the ID of the product to remove
     */
    @Transactional
    public void removeCartElement(Long id_prod) {
        Cart cart = getCartById();
        Map<Long, Integer> updated_items = cart.getItems();
        updated_items.remove(id_prod);
        cartRepository.save(cart);
    }

    /**
     * Updates the quantity of a product in the cart. If the quantity is zero, the product is removed from the cart.
     *
     * @param id_prod the ID of the product to update
     * @param quantity the new quantity of the product
     */
    @Transactional
    public void handlingCartQuantity(Long id_prod, int quantity) {
        Cart cart = getCartById();
        Map<Long, Integer> updated_cart = cart.getItems();

        if (quantity == 0) {
            removeCartElement(id_prod);
        } else {
            updated_cart.put(id_prod, quantity);
        }

        cartRepository.save(cart);
    }

    /**
     * Retrieves the items in the current user's cart, along with their quantities.
     *
     * @return a list of {@link CartItemsWrapper} representing the items in the cart
     */
    public List<CartItemsWrapper> getCartItems() {
        Cart cart = getCartById();
        Map<Long, Integer> cartItems = cart.getItems();
        List<ProductDTO> cartProducts = productService.getProductsByIds(cartItems.keySet());

        return cartProducts.stream()
                .map(productDTO -> new CartItemsWrapper(productDTO, cartItems.get(productDTO.id())))
                .collect(Collectors.toList());
    }
}

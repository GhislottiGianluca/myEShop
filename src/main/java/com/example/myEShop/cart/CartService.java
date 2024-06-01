package com.example.myEShop.cart;

import com.example.myEShop.appuser.AppUserRole;
import com.example.myEShop.appuser.AppUserService;
import com.example.myEShop.product.ProductCategories;
import com.example.myEShop.product.ProductDTO;
import com.example.myEShop.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartService {

    CartRepository cartRepository;
    AppUserService appUserService;
    ProductService productService;

    public CartService (CartRepository cartRepository,
                        AppUserService appUserService,
                        ProductService productService){
        this.cartRepository = cartRepository;
        this.appUserService = appUserService;
        this.productService = productService;
    }

    @Transactional
    public void addCartElement(Long id_prod, int quantity) {
        Cart cart = cartRepository.findCartByUserId(appUserService.getCurrentUserId());

        Map<Long, Integer> updated_items = cart.getItems();

        if(updated_items.containsKey(id_prod)){
            updated_items.merge(id_prod, quantity, Integer::sum);
        }else{
            updated_items.put(id_prod, quantity);
        }

        cart.setItems(updated_items);
        cartRepository.save(cart);
    }

    @Transactional
    public void deleteCartElement(Long id_prod) {

        Cart cart = cartRepository.findCartByUserId(appUserService.getCurrentUserId());

        Map<Long, Integer> updated_items = cart.getItems();

        if (updated_items.containsKey(id_prod)) {
            if (updated_items.get(id_prod) > 1) {
                updated_items.merge(id_prod, -1, Integer::sum);
            } else {
                updated_items.remove(id_prod);
            }
        }

        cart.setItems(updated_items);
        cartRepository.save(cart);
    }

    @Transactional
    public void removeCartElement(Long id_prod){

        Cart cart = cartRepository.findCartByUserId(appUserService.getCurrentUserId());

        Map<Long, Integer> updated_items = cart.getItems();

        updated_items.remove(id_prod);
        cartRepository.save(cart);
    }

    @Transactional
    public void handlingCartQuantity(Long id_prod, int quantity){
        Cart cart = cartRepository.findCartByUserId(appUserService.getCurrentUserId());

        Map<Long, Integer> updated_cart = cart.getItems();

        if(quantity == 0){
            removeCartElement(id_prod);
        }else{
            updated_cart.put(id_prod, quantity);
        }

        cartRepository.save(cart);
    }

    public List<CartItemsWrapper> getCartItems() {
        Cart cart = cartRepository.findCartByUserId(appUserService.getCurrentUserId());
        Map<Long, Integer> cartItems = cart.getItems();

        List<ProductDTO> cartProducts = productService.getProductsByIds(cartItems.keySet());

        return cartProducts.stream()
                .map(productDTO -> new CartItemsWrapper(productDTO, cartItems.get(productDTO.id())))
                .collect(Collectors.toList());
    }


}

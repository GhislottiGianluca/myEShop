package com.example.myEShop.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.Map;
@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public CartService (CartRepository cartRepository){this.cartRepository = cartRepository;}

    public void addCartElement(Long user_id, Long id_prod) {
        Cart cart = cartRepository.findCartByUserId(user_id);

        Map<Long, Integer> updated_items = cart.getItems();

        if(updated_items.containsKey(id_prod)){
            updated_items.merge(id_prod, 1, Integer::sum);
        }else{
            updated_items.put(id_prod, 1);
        }

        cart.setItems(updated_items);
    }

    public void deleteCartElement(Long user_id, Long id_prod) {

        Cart cart = cartRepository.findCartByUserId(user_id);

        Map<Long, Integer> updated_items = cart.getItems();

        if (updated_items.containsKey(id_prod)) {
            if (updated_items.get(id_prod) > 1) {
                updated_items.merge(id_prod, -1, Integer::sum);
            } else {
                updated_items.remove(id_prod);
            }
        }

        cart.setItems(updated_items);
    }
}

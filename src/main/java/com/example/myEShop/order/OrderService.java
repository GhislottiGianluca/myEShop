package com.example.myEShop.order;

import com.example.myEShop.appuser.AppUser;
import com.example.myEShop.appuser.AppUserRepository;
import com.example.myEShop.appuser.AppUserService;
import com.example.myEShop.cart.Cart;
import com.example.myEShop.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.myEShop.product.Product;
import com.example.myEShop.product.ProductRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;


    public OrderService(OrderRepository orderRepository, CartService cartService,
                        AppUserService appUserService, AppUserRepository appUserRepository){
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
    }

    public List<Order> getOrders(){
        Optional<List<Order>> orders = orderRepository.findOrdersByUserId(appUserService.getCurrentUserId());
        return orders.orElse(null);
    }

    public void createOrder() {
        Cart cart = cartService.getCartById();
        Optional<AppUser> appUser = appUserRepository.findAppUsersById(appUserService.getCurrentUserId());

        if(appUser.isPresent()) {
            Order newOrder = new Order(appUser.get(), cart.getItems(), OrderStatus.CREATED);
            orderRepository.save(newOrder);
        }
    }
}

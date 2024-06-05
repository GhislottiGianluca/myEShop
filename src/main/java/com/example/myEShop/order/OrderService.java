package com.example.myEShop.order;

import com.example.myEShop.appuser.AppUser;
import com.example.myEShop.appuser.AppUserRepository;
import com.example.myEShop.appuser.AppUserService;
import com.example.myEShop.cart.Cart;
import com.example.myEShop.cart.CartService;
import com.example.myEShop.product.ProductDTO;
import com.example.myEShop.product.ProductService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.myEShop.product.Product;
import com.example.myEShop.product.ProductRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;
    private final ProductService productService;


    public OrderService(OrderRepository orderRepository, CartService cartService,
                        AppUserService appUserService, AppUserRepository appUserRepository,
                        ProductService productService){
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
        this.productService = productService;
    }

    public List<OrderItemsWrapper> getOrders() {
        Long userId = appUserService.getCurrentUserId();

        return orderRepository.findOrdersByUserId(userId)
                .orElse(Collections.emptyList())
                .stream()
                .map(order -> {
                    List<ProductDTO> products = order.getItems().keySet().stream()
                            .map(productService::getProductById)
                            .collect(Collectors.toList());

                    List<Integer> quantities = new ArrayList<>(order.getItems().values());

                    return new OrderItemsWrapper(products, quantities, order.getCreatedAt(), order.getStatus());
                })
                .collect(Collectors.toList());
    }

    public void createOrder() {
        Cart cart = cartService.getCartById();
        Optional<AppUser> appUser = appUserRepository.findAppUsersById(appUserService.getCurrentUserId());

        if (appUser.isPresent()) {
            Map<Long, Integer> itemsCopy = new HashMap<>(cart.getItems());

            Order newOrder = new Order(appUser.get(), itemsCopy, OrderStatus.CREATED);
            orderRepository.save(newOrder);
        }

        cartService.removeAllCartElement();
    }
}

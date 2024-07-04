package com.example.myEShop.order;

import com.example.myEShop.appuser.AppUser;
import com.example.myEShop.appuser.AppUserRepository;
import com.example.myEShop.appuser.AppUserService;
import com.example.myEShop.cart.Cart;
import com.example.myEShop.cart.CartService;
import com.example.myEShop.product.ProductDTO;
import com.example.myEShop.product.ProductService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing orders.
 * <p>
 * This service handles the creation of new orders and retrieval of existing orders
 * for the current user.
 * </p>
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;
    private final ProductService productService;

    /**
     * Constructs a new OrderService with the specified dependencies.
     *
     * @param orderRepository the repository for managing order entities
     * @param cartService the service for managing carts
     * @param appUserService the service for managing application users
     * @param appUserRepository the repository for managing user entities
     * @param productService the service for managing products
     */
    public OrderService(OrderRepository orderRepository, CartService cartService,
                        AppUserService appUserService, AppUserRepository appUserRepository,
                        ProductService productService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
        this.productService = productService;
    }

    /**
     * Retrieves the list of orders for the current user.
     * <p>
     * This method fetches the orders associated with the current user, maps them
     * to {@link OrderItemsWrapper} objects, and returns them as a list.
     * </p>
     *
     * @return a list of {@link OrderItemsWrapper} representing the user's orders
     */
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

    /**
     * Creates a new order for the current user.
     * <p>
     * This method creates a new order using the items in the current user's cart,
     * saves the order to the repository, and then clears the cart.
     * </p>
     */
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

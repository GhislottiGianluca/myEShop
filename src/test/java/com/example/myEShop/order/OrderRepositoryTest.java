package com.example.myEShop.order;

import com.example.myEShop.appuser.AppUser;
import com.example.myEShop.appuser.AppUserRepository;
import com.example.myEShop.appuser.AppUserRole;
import com.example.myEShop.cart.Cart;
import com.example.myEShop.cart.CartRepository;
import com.example.myEShop.product.Product;
import com.example.myEShop.product.ProductBuilder;
import com.example.myEShop.product.ProductCategories;
import com.example.myEShop.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository underTest;

    @Test
    void itShouldFindOrdersByUserId() {
        // Given
        AppUser newUser = createUserWithCart();
        Product p1 = createExampleProduct();

        // Orders creation
        Order order1 = createOrderForUser(newUser, p1, 2);
        Order order2 = createOrderForUser(newUser, p1, 10);
        List<Order> expectedOrders = List.of(order1, order2);
        underTest.saveAll(expectedOrders);

        // When
        Optional<List<Order>> foundOrders = underTest.findOrdersByUserId(newUser.getId());

        // Then
        assertTrue(foundOrders.isPresent());
        assertEquals(expectedOrders, foundOrders.get());
    }

    private AppUser createUserWithCart() {
        Cart cart = new Cart();
        AppUser newUser = new AppUser("John", "Wayne", "johnwayne@gmail.com", "myStrongPassword", AppUserRole.USER, cart);
        cart.setUser(newUser);
        appUserRepository.save(newUser);
        return newUser;
    }

    private Product createExampleProduct() {
        Product p1 = new ProductBuilder("title")
                .description("description").category(ProductCategories.CUP).price(30.00).sold(10).build();
        productRepository.save(p1);
        return p1;
    }

    private Order createOrderForUser(AppUser user, Product product, int quantity) {
        Cart cart = user.getCart();
        Map<Long, Integer> userCart = new HashMap<>();
        userCart.put(product.getId(), quantity);
        cart.setItems(userCart);
        cartRepository.save(cart);
        Map<Long, Integer> cartItems = new HashMap<>(cart.getItems());
        Order order = new Order(user, cartItems, OrderStatus.CREATED);
        cart.setItems(new HashMap<>());
        cartRepository.save(cart);
        userCart.clear();
        return order;
    }
}
package com.example.myEShop.order;

import com.example.myEShop.appuser.AppUser;
import com.example.myEShop.appuser.AppUserRepository;
import com.example.myEShop.appuser.AppUserRole;
import com.example.myEShop.appuser.AppUserService;
import com.example.myEShop.cart.Cart;
import com.example.myEShop.cart.CartService;
import com.example.myEShop.product.ProductDTO;
import com.example.myEShop.product.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    CartService cartService;
    @Mock
    AppUserService appUserService;
    @Mock
    AppUserRepository appUserRepository;
    @Mock
    ProductService productService;
    OrderService underTest;


    AppUser appUser;
    Cart cart;
    Order order;

    @BeforeEach
    void setUp() {
        underTest = new OrderService(orderRepository, cartService, appUserService, appUserRepository, productService);

        // AppUser example instance creation
        cart = new Cart();
        appUser = new AppUser(
                "John", "Wayne", "johnwayne@gmail.com", "myStrongPassword", AppUserRole.USER, new Cart()
        );

        cart.setUser(appUser);
        appUser.setCart(cart);

        // Mock the current user ID retrieval
        lenient().when(appUserService.getCurrentUserId()).thenReturn(appUser.getId());

        // Initialize the cart with some items
        Map<Long, Integer> items = new HashMap<>();
        items.put(1L, 10);
        cart.setItems(items);

        appUserRepository.save(appUser);
        cartService.saveCart(cart);

    }

    @AfterEach
    void tearDown() {
        appUserRepository.deleteAll();
        cartService.removeAllCartElement();
        orderRepository.deleteAll();
    }

    @Test
    void canGetOrders() {
        // Given
        Long userId = appUser.getId();
        Order order = new Order(appUser, cart.getItems(), OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());

        // Mock the repository to return the order
        when(orderRepository.findOrdersByUserId(userId)).thenReturn(Optional.of(Arrays.asList(order)));

        // Mock the product service to return ProductDTO objects
        ProductDTO productDTO = new ProductDTO(1L, "Product 1", "Description", "image1", "image2", "image3", "image4", 100.0, "USD");
        when(productService.getProductById(1L)).thenReturn(productDTO);

        // When
        List<OrderItemsWrapper> orders = underTest.getOrders();

        // Then
        assertEquals(1, orders.size());
        OrderItemsWrapper orderItemsWrapper = orders.get(0);

        assertEquals(1, orderItemsWrapper.getProducts().size());
        assertEquals(productDTO, orderItemsWrapper.getProducts().get(0));
        assertEquals(10, orderItemsWrapper.getQuantities().get(0));
        assertEquals(order.getCreatedAt(), orderItemsWrapper.getCreatedAt());
        assertEquals(OrderStatus.CREATED, orderItemsWrapper.getStatus());

        // Verify the interactions
        verify(appUserService).getCurrentUserId();
        verify(orderRepository).findOrdersByUserId(userId);
        verify(productService).getProductById(1L);
    }

    @Test
    void canCreateOrder() {
        // Given
        Long userId = appUser.getId();
        when(appUserService.getCurrentUserId()).thenReturn(userId);
        when(appUserRepository.findAppUsersById(userId)).thenReturn(Optional.of(appUser));
        when(cartService.getCartById()).thenReturn(cart);

        // When
        underTest.createOrder();

        // Then
        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderArgumentCaptor.capture());
        Order capturedOrder = orderArgumentCaptor.getValue();

        assertEquals(appUser, capturedOrder.getAppUser());
        assertEquals(cart.getItems(), capturedOrder.getItems());
        assertEquals(OrderStatus.CREATED, capturedOrder.getStatus());

        verify(cartService).removeAllCartElement();
        verify(appUserService).getCurrentUserId();
        verify(appUserRepository).findAppUsersById(userId);
        verify(cartService).getCartById();
    }


}
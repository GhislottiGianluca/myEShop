package com.example.myEShop.cart;

import com.example.myEShop.appuser.AppUser;
import com.example.myEShop.appuser.AppUserRepository;
import com.example.myEShop.appuser.AppUserRole;
import com.example.myEShop.appuser.AppUserService;
import com.example.myEShop.product.ProductDTO;
import com.example.myEShop.product.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CartServiceTest {

  @Mock
  CartRepository cartRepository;
  @Mock
  AppUserService appUserService;
  @Mock
  ProductService productService;
  @Mock
  AppUserRepository appUserRepository;
  CartService underTest;


  AppUser appUser;
  Cart cart;

  @BeforeEach
  void setUp() {
    underTest = new CartService(cartRepository, appUserService, productService);

    // AppUser example instance creation
    cart = new Cart();
    appUser = new AppUser(
            "John", "Wayne", "johnwayne@gmail.com", "myStrongPassword", AppUserRole.USER, new Cart()
    );

    cart.setUser(appUser);
    appUser.setCart(cart);

    // Mock the current user ID retrieval
    lenient().when(appUserService.getCurrentUserId()).thenReturn(appUser.getId());
    // Mock repository findCartByUserId behavior
    lenient().when(cartRepository.findCartByUserId(appUser.getId())).thenReturn(cart);

    appUserRepository.save(appUser);
  }

  @AfterEach
  void tearDown() {
    appUserRepository.deleteAll();
  }

  @Test
  void canSaveCart() {
    // Given
    Cart c = new Cart();

    // When
    underTest.saveCart(c);

    // Then
    ArgumentCaptor<Cart> cartCaptured = ArgumentCaptor.forClass(Cart.class);
    verify(cartRepository).save(cartCaptured.capture());
    Cart captured = cartCaptured.getValue();
    assertEquals(c, captured);
  }

  @Test
  void canGetCartById() {
    // When
    Cart result = underTest.getCartById();

    // Then
    assertEquals(appUser.getId(), result.getUser().getId());
  }

  @Test
  void canAddCartElement() {
    // Given
    Long productId = 1L;
    int quantity = 9;

    // Initialize the cart items map
    Map<Long, Integer> items = new HashMap<>();
    items.put(productId, 2);  // Initial quantity for the product
    cart.setItems(items);

    // When
    underTest.addCartElement(productId, quantity);

    // Then
    ArgumentCaptor<Cart> cartCaptured = ArgumentCaptor.forClass(Cart.class);
    verify(cartRepository).save(cartCaptured.capture());
    Cart captured = cartCaptured.getValue();

    // Check if the product quantity has been updated correctly
    assertEquals(11, (int) captured.getItems().get(productId));
  }

  @Test
  void canAddNewCartElement() {
    // Given
    Long productId = 2L;  // New product ID (not present in the cart)
    int quantity = 5;

    cart.setItems(new HashMap<>());

    // When
    underTest.addCartElement(productId, quantity);

    // Then
    ArgumentCaptor<Cart> cartCaptured = ArgumentCaptor.forClass(Cart.class);
    verify(cartRepository).save(cartCaptured.capture());
    Cart captured = cartCaptured.getValue();

    assertEquals(quantity, (int) captured.getItems().get(productId));
  }

  @Test
  void canRemoveAllCartElements() {
    // Given
    Long productId = 1L;
    int initialQuantity = 5;

    // Initialize the cart with some items
    Map<Long, Integer> items = new HashMap<>();
    items.put(productId, initialQuantity);
    cart.setItems(items);

    // When
    underTest.removeAllCartElement();

    // Then
    ArgumentCaptor<Cart> cartCaptured = ArgumentCaptor.forClass(Cart.class);
    verify(cartRepository).save(cartCaptured.capture());
    Cart captured = cartCaptured.getValue();

    assertEquals(0, captured.getItems().size());
  }

  @Test
  void canRemoveCartElement() {
    // Given
    Long productId = 1L;
    int initialQuantity = 5;

    // Initialize the cart with some items
    Map<Long, Integer> items = new HashMap<>();
    items.put(productId, initialQuantity);
    cart.setItems(items);

    // When
    underTest.removeCartElement(productId);

    // Then
    ArgumentCaptor<Cart> cartCaptured = ArgumentCaptor.forClass(Cart.class);
    verify(cartRepository).save(cartCaptured.capture());
    Cart captured = cartCaptured.getValue();

    // Check if the product has been removed from the cart
    assertNull(captured.getItems().get(productId));
  }


  @Test
  void canHandleCartQuantity() {
    // Given
    Long productId = 1L;
    int initialQuantity = 5;
    int newQuantity = 10;

    // Initialize the cart with some items
    Map<Long, Integer> items = new HashMap<>();
    items.put(productId, initialQuantity);
    cart.setItems(items);

    // When: Update quantity
    underTest.handlingCartQuantity(productId, newQuantity);

    // Then: Verify updated quantity
    ArgumentCaptor<Cart> cartCaptured = ArgumentCaptor.forClass(Cart.class);
    verify(cartRepository).save(cartCaptured.capture());
    Cart captured = cartCaptured.getValue();
    assertEquals(newQuantity, (int) captured.getItems().get(productId));

    // When: Remove item by setting quantity to 0
    underTest.handlingCartQuantity(productId, 0);

    // Then: Verify item is removed
    verify(cartRepository, times(3)).save(cartCaptured.capture());
    captured = cartCaptured.getValue();
    assertNull(captured.getItems().get(productId));
  }

  @Test
  void canGetCartItems() {
    // Given
    Long productId1 = 1L;
    Long productId2 = 2L;
    int quantity1 = 3;
    int quantity2 = 5;

    // Initialize the cart with some items
    Map<Long, Integer> items = new HashMap<>();
    items.put(productId1, quantity1);
    items.put(productId2, quantity2);
    cart.setItems(items);

    // Mock the ProductService to return product details
    ProductDTO productDTO1 = new ProductDTO(productId1, "Product 1", "Description 1", "image1-1", "image1-2", "image1-3", "image1-4", 100.0, "CHF");
    ProductDTO productDTO2 = new ProductDTO(productId2, "Product 2", "Description 2", "image2-1", "image2-2", "image2-3", "image2-4", 200.0, "CHF");
    List<ProductDTO> productDTOList = Arrays.asList(productDTO1, productDTO2);

    when(productService.getProductsByIds(items.keySet())).thenReturn(productDTOList);

    // When
    List<CartItemsWrapper> cartItemsWrappers = underTest.getCartItems();

    // Then
    assertEquals(2, cartItemsWrappers.size());
    assertEquals(productDTO1, cartItemsWrappers.get(0).getProduct());
    assertEquals(quantity1, cartItemsWrappers.get(0).getQuantity());
    assertEquals(productDTO2, cartItemsWrappers.get(1).getProduct());
    assertEquals(quantity2, cartItemsWrappers.get(1).getQuantity());
  }



}
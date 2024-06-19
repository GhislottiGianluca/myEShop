package com.example.myEShop.cart;

import com.example.myEShop.appuser.AppUser;
import com.example.myEShop.appuser.AppUserRepository;
import com.example.myEShop.appuser.AppUserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CartRepositoryTest {

    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    CartRepository underTest;
    @Test
    void itShouldFindCartByUserId() {
        // Given
        Cart cart = new Cart();
        AppUser newUser = new AppUser("John", "Wayne", "johnwayne@gmail.com", "myStrongPassword",AppUserRole.USER, cart);
        cart.setUser(newUser);
        appUserRepository.save(newUser);

        // When
        Cart userCart = underTest.findCartByUserId(newUser.getId());

        // Then
        assertEquals(cart, userCart);
    }
}
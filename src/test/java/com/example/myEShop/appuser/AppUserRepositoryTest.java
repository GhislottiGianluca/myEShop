package com.example.myEShop.appuser;

import com.example.myEShop.cart.Cart;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository underTest;
    @Autowired
    private EntityManager entityManager;

    private AppUser appUser;

    @BeforeEach
    void setUp() {
        createAppUserInstance();
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    public void createAppUserInstance(){
        Cart cart = new Cart();
        appUser = new AppUser(
                "John", "Wayne", "johnwayne@gmail.com", "myStrongPassword", AppUserRole.USER, new Cart()
        );

        cart.setUser(appUser);
        appUser.setCart(cart);

        underTest.save(appUser);
    }

    @Test
    void itShouldFindAppUserByEmail() {
        // Given
        String existEmail = "johnwayne@gmail.com";
        String notExistEmail = "somethingwrong@gmail.com";

        // When
        Optional<AppUser> found = underTest.findByEmail(existEmail);
        Optional<AppUser> notfound = underTest.findByEmail(notExistEmail);

        // Then
        assertTrue(found.isPresent());
        assertEquals(appUser, found.get());
        assertFalse(notfound.isPresent());
    }

    @Test
    void itShouldEnableAppUser() {
        // Given
        String email = "johnwayne@gmail.com";
        Optional<AppUser> appUser = underTest.findByEmail(email);

        // When
        underTest.enableAppUser(email);
        entityManager.flush();
        entityManager.refresh(appUser.get());
        appUser = underTest.findByEmail(email);

        // When & Then
        if (appUser.isPresent()) {
            assertTrue(appUser.get().isEnabled());
        }else{
            throw new IllegalStateException("appUser in AppUserRepositoryTest doesn't exists.");
        }
    }
}
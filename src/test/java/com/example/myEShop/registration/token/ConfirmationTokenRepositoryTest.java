package com.example.myEShop.registration.token;

import com.example.myEShop.appuser.AppUser;
import com.example.myEShop.appuser.AppUserRepository;
import com.example.myEShop.appuser.AppUserRole;
import com.example.myEShop.cart.Cart;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ConfirmationTokenRepositoryTest {

    @Autowired
    private ConfirmationTokenRepository underTest;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private EntityManager entityManager;

    private static String token;
    private AppUser appUser;

    @BeforeAll
    static void setUpAll() {
        token = UUID.randomUUID().toString();
    }
    @BeforeEach
    void setUp() {
        appUser = createAndSaveAppUser("johnwayne@gmail.com", "myStrongPassword");
        ConfirmationToken confirmationToken = createAndSaveConfirmationToken(token, appUser);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    private AppUser createAndSaveAppUser(String email, String password) {
        Cart cart = new Cart();
        AppUser newUser = new AppUser("John", "Wayne", email, password, AppUserRole.USER, cart);
        cart.setUser(newUser);
        appUserRepository.save(newUser);
        return newUser;
    }

    private ConfirmationToken createAndSaveConfirmationToken(String token, AppUser user) {
        ConfirmationToken newToken = new ConfirmationToken(
                token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user
        );
        underTest.save(newToken);
        return newToken;
    }

    @Test
    void itShouldFindTokenByAppUser() {
        // When
        ConfirmationToken foundToken = underTest.findByToken(token).orElse(null);

        // Then
        assertNotNull(foundToken, "Token should be found.");
        assertEquals(appUser.getId(), foundToken.getAppUser().getId(), "Token should belong to the correct user.");
    }

    @Test
    void itShouldUpdateConfirmedAt() {
        // Given
        LocalDateTime confirmedAtTime = LocalDateTime.now();

        // When
        underTest.updateConfirmedAt(token, confirmedAtTime);
        Optional<ConfirmationToken> updatedToken = underTest.findByToken(token);
        entityManager.flush();
        entityManager.refresh(updatedToken.get());

        // Then
        if(updatedToken.isPresent()){
            assertEquals(confirmedAtTime, updatedToken.get().getConfirmedAt());
        }else{
            throw new IllegalStateException("Token not found");
        }
    }
}

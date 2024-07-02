package com.example.myEShop.registration.token;

import com.example.myEShop.appuser.AppUser;
import com.example.myEShop.appuser.AppUserRepository;
import com.example.myEShop.appuser.AppUserRole;
import com.example.myEShop.cart.Cart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfirmationTokenServiceTest {

    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Mock
    private AppUserRepository appUserRepository;
    private ConfirmationTokenService underTest;
    private static String token;
    private AppUser appUser;
    private ConfirmationToken confirmationToken;

    @BeforeAll
    static void setUpAll() {
        token = UUID.randomUUID().toString();
    }
    @BeforeEach
    void setUp() {
        underTest = new ConfirmationTokenService(confirmationTokenRepository);
        appUser = createAndSaveAppUser("johnwayne@gmail.com", "myStrongPassword");
        confirmationToken = createAndSaveConfirmationToken(token, appUser);
    }

    @AfterEach
    void tearDown() {
        confirmationTokenRepository.deleteAll();
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
        confirmationTokenRepository.save(newToken);
        return newToken;
    }

    @Test
    void canFindTokenByAppUser() {
        // Given
        Long userId = appUser.getId();
        String expectedToken = token;
        when(confirmationTokenRepository.findTokenByAppUser(userId)).thenReturn(expectedToken);

        // When
        String actualToken = underTest.findTokenByAppUser(userId);

        // Then
        assertEquals(expectedToken, actualToken);
        verify(confirmationTokenRepository).findTokenByAppUser(userId);
    }

    @Test
    void canSaveConfirmationToken() {

        // When
        underTest.saveConfirmationToken(confirmationToken);

        // Then
        ArgumentCaptor<ConfirmationToken> tokenArgumentCaptor = ArgumentCaptor.forClass(ConfirmationToken.class);
        verify(confirmationTokenRepository, times(2)).save(tokenArgumentCaptor.capture());

        ConfirmationToken capturedToken = tokenArgumentCaptor.getValue();
        assertEquals(confirmationToken, capturedToken);
    }

    @Test
    void canGetToken() {
        // Given
        String tokenValue = token;
        ConfirmationToken expectedToken = new ConfirmationToken(
                tokenValue, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), appUser
        );
        when(confirmationTokenRepository.findByToken(tokenValue)).thenReturn(Optional.of(expectedToken));

        // When
        Optional<ConfirmationToken> actualToken = underTest.getToken(tokenValue);

        // Then
        assertTrue(actualToken.isPresent());
        assertEquals(expectedToken, actualToken.get());
        verify(confirmationTokenRepository).findByToken(tokenValue);
    }

    @Test
    void canSetConfirmedAt() {
        // Given
        String tokenValue = token;
        LocalDateTime now = LocalDateTime.now();

        // When
        underTest.setConfirmedAt(tokenValue);

        // Then
        ArgumentCaptor<String> tokenArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<LocalDateTime> timeArgumentCaptor = ArgumentCaptor.forClass(LocalDateTime.class);

        verify(confirmationTokenRepository).updateConfirmedAt(tokenArgumentCaptor.capture(), timeArgumentCaptor.capture());

        assertEquals(tokenValue, tokenArgumentCaptor.getValue());
        assertTrue(now.isBefore(timeArgumentCaptor.getValue()) || now.isEqual(timeArgumentCaptor.getValue()));
    }



}
package com.example.myEShop.appuser;

import com.example.myEShop.cart.Cart;
import com.example.myEShop.cart.CartService;
import com.example.myEShop.registration.token.ConfirmationTokenService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private ConfirmationTokenService confirmationTokenService;
    @Mock
    private CartService cartService;
    @Mock
    private AppUserDTOMapper appUserDTOMapper;
    private AppUserService underTest;


    private AppUser appUser;

    @BeforeEach
    void setUp() {
        // AppUserService initialisation
        underTest = new AppUserService(appUserRepository, bCryptPasswordEncoder, confirmationTokenService, cartService, appUserDTOMapper);

        // AppUser example instance creation
        Cart cart = new Cart();
        appUser = new AppUser(
                "John", "Wayne", "johnwayne@gmail.com", "myStrongPassword", AppUserRole.USER, new Cart()
        );

        cart.setUser(appUser);
        appUser.setCart(cart);

        appUserRepository.save(appUser);
    }

    @AfterEach
    void tearDown() {
        appUserRepository.deleteAll();
    }

    @Test
    void canLoadUserByUsername() {
        // Given
        String appUserEmail = "johnwayne@gmail.com";
        when(appUserRepository.findByEmail("johnwayne@gmail.com")).thenReturn(Optional.of(appUser));


        // When
        underTest.loadUserByUsername(appUserEmail);

        // Then
        ArgumentCaptor<String> emailArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(appUserRepository).findByEmail(emailArgumentCaptor.capture());
        String capturedEmail = emailArgumentCaptor.getValue();
        assertEquals(appUserEmail, capturedEmail);
    }

    @Test
    void canLoadUserByUsername_withNonExistingUser() {
        // Given
        String nonExistingEmail = "nonexisting@gmail.com";
        when(appUserRepository.findByEmail(nonExistingEmail)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(UsernameNotFoundException.class, () -> underTest.loadUserByUsername(nonExistingEmail));
    }

    @Test
    void canSignUpUser() {
        // When
        underTest.signUpUser(appUser);

        // Then
        ArgumentCaptor<AppUser> userInstanceArgumentCaptor = ArgumentCaptor.forClass(AppUser.class);
        verify(appUserRepository, times(2)).save(userInstanceArgumentCaptor.capture());
        AppUser capturedUser = userInstanceArgumentCaptor.getValue();
        assertEquals(appUser, capturedUser);
    }

    @Test
    void alreadyExistsUserWhenSignUp(){
        // Given
        AppUser existingUser = new AppUser(
                "John", "Wayne", "johnwayne@gmail.com", "myStrongPassword", AppUserRole.USER, new Cart()
        );
        existingUser.setEnabled(true);

        when(appUserRepository.findByEmail("johnwayne@gmail.com")).thenReturn(Optional.of(existingUser));

        // When & Then
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            underTest.signUpUser(existingUser);
        }, "User already exists");

        assertEquals("User already exists", thrown.getMessage());
    }

    @Test
    void canEnableAppUser() {
        // Given
        String appUserEmail = "johnwayne@gmail.com";

        // When
        underTest.enableAppUser(appUserEmail);

        // Then
        ArgumentCaptor<String> emailCaptured = ArgumentCaptor.forClass(String.class);
        verify(appUserRepository).enableAppUser(emailCaptured.capture());
        String capturedEmail = emailCaptured.getValue();
        assertEquals(appUserEmail, capturedEmail);
    }

    @Test
    void canGetCurrentUserId_withAuthenticatedUser() {
        // Given
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        UserDetails userDetails = mock(UserDetails.class);
        AppUser appUser = new AppUser();
        appUser.setId(1L);

        try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder = mockStatic(SecurityContextHolder.class)) {
            mockedSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.isAuthenticated()).thenReturn(true);
            when(authentication.getPrincipal()).thenReturn(userDetails);
            when(userDetails.getUsername()).thenReturn("johnwayne@gmail.com");
            when(appUserRepository.findByEmail("johnwayne@gmail.com")).thenReturn(Optional.of(appUser));

            // When
            Long userId = underTest.getCurrentUserId();

            // Then
            assertNotNull(userId);
            assertEquals(appUser.getId(), userId);
        }
    }


    @Test
    void canGetCurrentUserId_withNoAuthenticatedUser() {
        // Given
        SecurityContext securityContext = mock(SecurityContext.class);

        try (MockedStatic<SecurityContextHolder> mocked = mockStatic(SecurityContextHolder.class)) {
            mocked.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(null);

            // When
            Long userId = underTest.getCurrentUserId();

            // Then
            assertNull(userId);
        }
    }

    @Test
    void canGetAppUserInformation() {
        // Given
        Long userId = appUser.getId();
        when(appUserRepository.findAppUsersById(userId)).thenReturn(Optional.of(appUser));

        AppUserDTO appUserDTO = new AppUserDTO(
                appUser.getFirstName(),
                appUser.getLastName(),
                appUser.getEmail()
        );
        when(appUserDTOMapper.apply(appUser)).thenReturn(appUserDTO);

        AppUserService spyUnderTest = spy(underTest);
        doReturn(userId).when(spyUnderTest).getCurrentUserId();

        // When
        AppUserDTO result = spyUnderTest.getAppUserInformation();

        // Then
        assertNotNull(result);
        assertEquals(appUserDTO, result);
        verify(appUserRepository).findAppUsersById(userId);
        verify(appUserDTOMapper).apply(appUser);
    }


    @Test
    void canDeleteAppUser() {
        // Given
        Long expectedUserId = 1L;
        AppUserService spyUnderTest = spy(underTest);
        doReturn(expectedUserId).when(spyUnderTest).getCurrentUserId();

        // When
        spyUnderTest.deleteAppUser();

        // Then
        verify(appUserRepository).deleteById(expectedUserId);
    }

    @Test
    void canUpdateAppUser() {
        // Given
        Long userId = appUser.getId();
        AppUserRequest appUserRequest = new AppUserRequest("Jane", "Doe");

        when(appUserRepository.findAppUsersById(userId)).thenReturn(Optional.of(appUser));

        AppUserService spyUnderTest = spy(underTest);
        doReturn(userId).when(spyUnderTest).getCurrentUserId();

        // When
        spyUnderTest.updateAppUser(appUserRequest);

        // Then
        assertEquals("Jane", appUser.getFirstName());
        assertEquals("Doe", appUser.getLastName());
        verify(appUserRepository, times(2)).save(appUser);
    }

    @Test
    void updateAppUserWithPartialChanges() {
        // Given
        Long userId = appUser.getId();
        AppUserRequest appUserRequest = new AppUserRequest("Jane", null);

        when(appUserRepository.findAppUsersById(userId)).thenReturn(Optional.of(appUser));

        AppUserService spyUnderTest = spy(underTest);
        doReturn(userId).when(spyUnderTest).getCurrentUserId();

        // When
        spyUnderTest.updateAppUser(appUserRequest);

        // Then
        assertEquals("Jane", appUser.getFirstName());
        assertEquals("Wayne", appUser.getLastName());
        verify(appUserRepository, times(2)).save(appUser);
    }

    @Test
    void updateAppUserWithNoAuthenticatedUser() {
        // Given
        AppUserRequest appUserRequest = new AppUserRequest("Jane", "Doe");

        AppUserService spyUnderTest = spy(underTest);
        doReturn(null).when(spyUnderTest).getCurrentUserId();

        // When
        spyUnderTest.updateAppUser(appUserRequest);

        // Then
        verify(appUserRepository, never()).findAppUsersById(anyLong());
        verify(appUserRepository).save(any(AppUser.class));
    }

}
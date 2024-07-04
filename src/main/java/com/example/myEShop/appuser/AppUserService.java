package com.example.myEShop.appuser;

import com.example.myEShop.registration.token.ConfirmationToken;
import com.example.myEShop.registration.token.ConfirmationTokenService;
import com.example.myEShop.cart.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.HashMap;

/**
 * Service class for managing application users.
 * <p>
 * This service handles user-related operations such as registration,
 * authentication, and user data management.
 * </p>
 */
@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final @Lazy CartService cartService;
    private final AppUserDTOMapper appUserDTOMapper;

    /**
     * Loads a user by their email.
     * <p>
     * This method is used by Spring Security to authenticate a user.
     * </p>
     *
     * @param email the email of the user
     * @return UserDetails the details of the user
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByEmail(email);
        if (user.isPresent()) {
            AppUser userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        } else {
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
        }
    }

    /**
     * Gets the role of a user as a string.
     *
     * @param user the user whose role is to be retrieved
     * @return the role of the user as a string
     */
    private String getRoles(AppUser user) {
        if (user.getRole() == null) {
            return AppUserRole.USER.toString();
        }
        return user.getRole().toString();
    }

    /**
     * Registers a new user and returns a confirmation token.
     * <p>
     * This method handles user registration, including password encoding
     * and generating a confirmation token.
     * </p>
     *
     * @param appUser the user to sign up
     * @return the confirmation token
     * @throws IllegalStateException if the user already exists with different data or is already enabled
     */
    public String signUpUser(AppUser appUser) {
        Optional<AppUser> optionalExistingUser = appUserRepository.findByEmail(appUser.getEmail());

        if (optionalExistingUser.isPresent()) {
            AppUser existingUser = optionalExistingUser.get();
            boolean isDataEqual = Objects.equals(existingUser.getFirstName(), appUser.getFirstName()) &&
                    Objects.equals(existingUser.getLastName(), appUser.getLastName());

            if (existingUser.getEnabled() || !isDataEqual) {
                throw new IllegalStateException("User already exists");
            } else {
                return confirmationTokenService.findTokenByAppUser(existingUser.getId());
            }
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        Cart cart = new Cart(appUser, new HashMap<>());
        appUser.setCart(cart);
        appUserRepository.save(appUser);

        cart.setUser(appUser);
        cartService.saveCart(cart);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    /**
     * Enables a user by their email.
     * <p>
     * This method sets the enabled status of a user to true.
     * </p>
     *
     * @param email the email of the user to enable
     */
    public void enableAppUser(String email) {
        appUserRepository.enableAppUser(email);
    }

    /**
     * Retrieves the ID of the current authenticated user.
     *
     * @return the ID of the current user, or null if not authenticated
     */
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String email = ((UserDetails) principal).getUsername();
                Optional<AppUser> user = appUserRepository.findByEmail(email);
                return user.map(AppUser::getId).orElse(null);
            }
        }
        return null;
    }

    /**
     * Retrieves the information of the current authenticated user.
     *
     * @return an {@link AppUserDTO} containing the user's information, or null if not found
     */
    public AppUserDTO getAppUserInformation() {
        Optional<AppUser> user = appUserRepository.findAppUsersById(getCurrentUserId());
        return user.map(appUserDTOMapper).orElse(null);
    }

    /**
     * Deletes the current authenticated user.
     */
    public void deleteAppUser() {
        appUserRepository.deleteById(getCurrentUserId());
    }

    /**
     * Updates the current authenticated user's information.
     * <p>
     * This method updates the user's first name and/or last name if they are different
     * from the current values.
     * </p>
     *
     * @param appUserRequest the request containing the updated user information
     */
    public void updateAppUser(AppUserRequest appUserRequest) {
        Optional<AppUser> appUser = appUserRepository.findAppUsersById(getCurrentUserId());
        if (appUser.isPresent()) {
            String originalFirstName = appUser.get().getFirstName();
            if ((appUserRequest.getFirstName() != null)
                    && (!originalFirstName.equals(appUserRequest.getFirstName()))) {
                appUser.get().setFirstName(appUserRequest.getFirstName());
            }

            String originalLastName = appUser.get().getLastName();
            if ((appUserRequest.getLastName() != null)
                    && (!originalLastName.equals(appUserRequest.getLastName()))) {
                appUser.get().setLastName(appUserRequest.getLastName());
            }

            appUserRepository.save(appUser.get());
        }
    }
}

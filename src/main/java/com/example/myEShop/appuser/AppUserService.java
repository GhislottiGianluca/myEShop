package com.example.myEShop.appuser;

import com.example.myEShop.registration.token.ConfirmationToken;
import com.example.myEShop.registration.token.ConfirmationTokenService;
import com.example.myEShop.cart.*;
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

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final CartService cartService;
    private final AppUserDTOMapper appUserDTOMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByEmail(email);
        if(user.isPresent()){
            AppUser userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        }else {
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
        }
    }

    private String getRoles(AppUser user){
        if(user.getRole() == null){
            return AppUserRole.USER.toString();
        }
        return user.getRole().toString();
    }

    public String signUpUser(AppUser appUser){
        Optional<AppUser> optionalExistingUser = appUserRepository.findByEmail(appUser.getEmail());

        if (optionalExistingUser.isPresent()) {
            AppUser existingUser = optionalExistingUser.get();

            boolean isDataEqual = Objects.equals(existingUser.getFirstName(), appUser.getFirstName()) &&
                                  Objects.equals(existingUser.getLastName(), appUser.getLastName());

            if (existingUser.getEnabled() || !isDataEqual) {

                throw new IllegalStateException("User already exists");
            }else{

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

    public void enableAppUser(String email) {
        appUserRepository.enableAppUser(email);
    }

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

    public AppUserDTO getAppUserInformation(){
        Optional<AppUser> user = appUserRepository.findAppUsersById(getCurrentUserId());
        return user.map(appUserDTOMapper).orElse(null);
    }

    public void deleteAppUser(){
        appUserRepository.deleteById(getCurrentUserId());
    }

    public void updateAppUser(AppUserRequest appUserRequest){
        Optional<AppUser> appUser = appUserRepository.findAppUsersById(getCurrentUserId());
        if(appUser.isPresent()) {
            String originalFirstName = appUser.get().getFirstName();
            if((appUserRequest.getFirstName() != null)
                    && (!originalFirstName.equals(appUserRequest.getFirstName()))){

                appUser.get().setFirstName(appUserRequest.getFirstName());
            }

            String originalLastName = appUser.get().getLastName();
            if((appUserRequest.getLastName() != null)
                    && (!originalLastName.equals(appUserRequest.getLastName()))){

                appUser.get().setLastName(appUserRequest.getLastName());
            }

            appUserRepository.save(appUser.get());
        }
    }
}

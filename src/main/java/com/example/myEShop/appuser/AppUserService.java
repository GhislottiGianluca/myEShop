package com.example.myEShop.appuser;

import com.example.myEShop.registration.token.ConfirmationToken;
import com.example.myEShop.registration.token.ConfirmationTokenRepository;
import com.example.myEShop.registration.token.ConfirmationTokenService;
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

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final AppUserRepository appUserRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
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

                return confirmationTokenRepository.findTokenByAppUser(existingUser.getId());
            }
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

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

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}

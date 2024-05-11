package com.example.myEShop.appuser;

import com.example.myEShop.registration.token.ConfirmationToken;
import com.example.myEShop.registration.token.ConfirmationTokenRepository;
import com.example.myEShop.registration.token.ConfirmationTokenService;
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
        Optional<AppUser> user = appUserRepository.findByEmail(email);
        if(user.isPresent()){
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        }else {
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
        }
    }

    private String[] getRoles(AppUser user){
        if(user.getRole() == null){
            return new String[] {"USER"};
        }
        return user.getRole().split(",");
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

    public void enableAppUser(String email) {
        appUserRepository.enableAppUser(email);
    }
}

package com.example.myEShop.registration.token;

import com.example.myEShop.appuser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service class for managing confirmation tokens.
 * <p>
 * Provides methods for saving, retrieving, and updating confirmation tokens.
 * </p>
 */
@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    /**
     * Retrieves the token string associated with a specific app user ID.
     *
     * @param userId the ID of the app user
     * @return the token string associated with the given app user ID
     */
    public String findTokenByAppUser(Long userId) {
        return confirmationTokenRepository.findTokenByAppUser(userId);
    }

    /**
     * Saves a new confirmation token to the repository.
     *
     * @param token the {@link ConfirmationToken} to be saved
     */
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    /**
     * Retrieves a confirmation token by its token string.
     *
     * @param token the token string
     * @return an {@link Optional} containing the found {@link ConfirmationToken}
     */
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    /**
     * Updates the confirmation time of a specific token to the current time.
     *
     * @param token the token string
     */
    public void setConfirmedAt(String token) {
        confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}

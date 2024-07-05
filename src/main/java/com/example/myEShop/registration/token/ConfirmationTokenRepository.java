package com.example.myEShop.registration.token;

import com.example.myEShop.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Repository interface for managing {@link ConfirmationToken} entities.
 * <p>
 * Provides methods for common CRUD operations as well as custom queries for working with confirmation tokens.
 * </p>
 */
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    /**
     * Finds a confirmation token by its token string.
     *
     * @param token the token string
     * @return an {@link Optional} containing the found {@link ConfirmationToken}, or empty if not found
     */
    Optional<ConfirmationToken> findByToken(String token);

    /**
     * Finds the token string associated with a specific app user ID.
     *
     * @param id the ID of the app user
     * @return the token string associated with the given app user ID
     */
    @Transactional
    @Query("SELECT c.token FROM ConfirmationToken c WHERE c.appUser.id = ?1")
    String findTokenByAppUser(Long id);

    /**
     * Updates the confirmation time of a specific token.
     * <p>
     * This method is annotated with {@link Modifying} and {@link Transactional} for the update operation.
     * </p>
     *
     * @param token the token string
     * @param confirmedAt the time the token was confirmed
     */
    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c SET c.confirmedAt = ?2 WHERE c.token = ?1")
    void updateConfirmedAt(String token, LocalDateTime confirmedAt);
}

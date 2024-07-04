package com.example.myEShop.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for {@link AppUser} entities.
 * <p>
 * This interface provides methods for CRUD operations and custom queries on AppUser entities.
 * </p>
 */
@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    /**
     * Finds an AppUser by their email.
     *
     * @param email the email of the user
     * @return an {@link Optional} containing the found AppUser, or empty if not found
     */
    Optional<AppUser> findByEmail(String email);

    /**
     * Finds an AppUser by their ID.
     *
     * @param id the ID of the user
     * @return an {@link Optional} containing the found AppUser, or empty if not found
     */
    Optional<AppUser> findAppUsersById(Long id);

    /**
     * Enables an AppUser by setting their enabled status to true based on their email.
     * <p>
     * This method is transactional and modifies the AppUser entity.
     * </p>
     *
     * @param email the email of the user to be enabled
     */
    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = true WHERE a.email = ?1")
    void enableAppUser(String email);
}

package com.example.myEShop.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository interface for managing {@link Cart} entities.
 * <p>
 * This interface provides methods for CRUD operations and custom queries on Cart entities.
 * </p>
 */
@Repository
@Transactional(readOnly = true)
public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * Finds a cart by the user ID.
     * <p>
     * This method retrieves a cart associated with the given user ID.
     * </p>
     *
     * @param id the ID of the user
     * @return the cart associated with the specified user ID
     */
    @Query("SELECT c FROM Cart c WHERE c.user.id = ?1")
    Cart findCartByUserId(Long id);
}

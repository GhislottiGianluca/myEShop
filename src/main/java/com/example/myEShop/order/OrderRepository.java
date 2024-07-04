package com.example.myEShop.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link Order} entities.
 * <p>
 * This interface provides methods for CRUD operations and custom queries on Order entities.
 * </p>
 */
@Repository
@Transactional(readOnly = true)
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Finds orders by the user ID.
     * <p>
     * This method retrieves a list of orders associated with the specified user ID.
     * </p>
     *
     * @param id the ID of the user
     * @return an {@link Optional} containing a list of orders, or empty if no orders are found
     */
    @Query("SELECT o FROM Order o WHERE o.appUser.id = ?1")
    Optional<List<Order>> findOrdersByUserId(Long id);
}

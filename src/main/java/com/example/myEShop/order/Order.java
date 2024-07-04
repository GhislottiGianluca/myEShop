package com.example.myEShop.order;

import com.example.myEShop.appuser.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Entity class representing an order in the eShop system.
 * <p>
 * This class maps to the "orders" table in the database and contains information about
 * the user who placed the order, the items in the order, the order status, and the creation time.
 * </p>
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    /**
     * The unique identifier for the order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_sequence")
    @SequenceGenerator(name = "orders_sequence", sequenceName = "orders_sequence", allocationSize = 1)
    private Long id;

    /**
     * The user who placed the order.
     */
    @ManyToOne
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;

    /**
     * The items in the order.
     * <p>
     * This is a collection of key-value pairs where the key is the product ID and the value
     * is the quantity of that product in the order.
     * </p>
     */
    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Long, Integer> items;

    /**
     * The timestamp when the order was created.
     */
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The status of the order.
     * <p>
     * This field is an enumeration that indicates whether the order is created, shipped, or delivered.
     * </p>
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    /**
     * Constructs a new Order with the specified user, items, and status.
     * <p>
     * The creation time is set to the current time.
     * </p>
     *
     * @param appUser the user who placed the order
     * @param items the items in the order
     * @param status the status of the order
     */
    public Order(AppUser appUser, Map<Long, Integer> items, OrderStatus status) {
        this.appUser = appUser;
        this.items = items;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }
}

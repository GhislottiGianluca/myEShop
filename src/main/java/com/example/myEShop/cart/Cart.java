package com.example.myEShop.cart;

import com.example.myEShop.appuser.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;
import java.util.HashMap;

/**
 * Entity class representing a shopping cart.
 * <p>
 * This class maps to the "cart" table in the database and contains information about the user
 * associated with the cart and the items in the cart.
 * </p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
@Entity
public class Cart {

    /**
     * Cart Id.
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * The user associated with the cart.
     * <p>
     * This is a one-to-one relationship where the cart shares the same ID as the user.
     * The {@link AppUser} entity is lazily fetched.
     * </p>
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    /**
     * The items in the cart.
     * <p>
     * This is a collection of key-value pairs where the key is the product ID and the value
     * is the quantity of that product in the cart.
     * </p>
     */
    @ElementCollection
    @CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"))
    @MapKeyColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Long, Integer> items;

    /**
     * Constructs a new Cart with the specified user and items.
     *
     * @param user the user associated with the cart
     * @param items the items in the cart
     */
    public Cart(AppUser user, HashMap<Long, Integer> items) {
        this.user = user;
        this.items = items;
    }
}

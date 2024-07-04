package com.example.myEShop.appuser;

import com.example.myEShop.cart.Cart;
import com.example.myEShop.order.Order;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Entity class representing an application user.
 * Implements {@link UserDetails} for Spring Security integration.
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {

    /**
     * Sequence generator for user IDs.
     */
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    /**
     * The first name of the user.
     */
    private String firstName;

    /**
     * The last name of the user.
     */
    private String lastName;

    /**
     * The email of the user, used as the username.
     */
    private String email;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The role of the user.
     */
    @Enumerated(EnumType.STRING)
    private AppUserRole role;

    /**
     * Whether the user account is locked.
     */
    private Boolean locked = false;

    /**
     * Whether the user account is enabled.
     */
    private Boolean enabled = false;

    /**
     * The cart associated with the user.
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    private Cart cart;

    /**
     * The list of orders placed by the user.
     */
    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private List<Order> orders;

    /**
     * Constructor for creating a new AppUser.
     *
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param email the email of the user
     * @param password the password of the user
     * @param appUserRole the role of the user
     * @param cart the cart associated with the user
     */
    public AppUser(String firstName,
                   String lastName,
                   String email,
                   String password,
                   AppUserRole appUserRole,
                   Cart cart) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = appUserRole;
        this.cart = cart;
    }

    /**
     * Returns the authorities granted to the user.
     *
     * @return a collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
        return Collections.singletonList(authority);
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username used to authenticate the user.
     *
     * @return the email
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Indicates whether the user's account has expired.
     *
     * @return true if the account is non-expired, false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     *
     * @return true if the account is non-locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     *
     * @return true if the credentials are non-expired, false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     *
     * @return true if the user is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

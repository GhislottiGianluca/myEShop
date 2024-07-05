package com.example.myEShop.registration.token;

import com.example.myEShop.appuser.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entity representing a confirmation token used for user registration.
 * <p>
 * This token is generated when a user registers and is used to verify the user's email address.
 * It has an expiration time and can be confirmed upon successful verification.
 * </p>
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    /**
     * The unique identifier for the confirmation token.
     */
    @SequenceGenerator(
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence"
    )
    private Long id;

    /**
     * The confirmation token string.
     */
    @Column(nullable = false)
    private String token;

    /**
     * The timestamp when the token was created.
     */
    @Column(nullable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp when the token expires.
     */
    @Column(nullable = false)
    private LocalDateTime expiresAt;

    /**
     * The timestamp when the token was confirmed, if it has been confirmed.
     */
    private LocalDateTime confirmedAt;

    /**
     * The user associated with this confirmation token.
     */
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;

    /**
     * Constructs a new ConfirmationToken with the specified details.
     *
     * @param token the confirmation token string
     * @param createdAt the timestamp when the token was created
     * @param expiresAt the timestamp when the token expires
     * @param appUser the user associated with this confirmation token
     */
    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             AppUser appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }
}

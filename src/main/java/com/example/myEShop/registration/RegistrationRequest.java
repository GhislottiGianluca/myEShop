package com.example.myEShop.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A data transfer object representing a user registration request.
 * <p>
 * This class encapsulates the necessary information for registering a new user,
 * including their first name, last name, email, and password.
 * </p>
 * <p>
 * It is immutable and uses Lombok annotations to generate boilerplate code
 * such as getters, constructors, equals, hashCode, and toString methods.
 * </p>
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    /**
     * The first name of the user.
     */
    private final String firstName;

    /**
     * The last name of the user.
     */
    private final String lastName;

    /**
     * The email address of the user.
     */
    private final String email;

    /**
     * The password for the user's account.
     */
    private final String password;
}

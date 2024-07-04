package com.example.myEShop.appuser;

/**
 * Data Transfer Object for application users.
 * <p>
 * This record holds the basic information of a user.
 * </p>
 *
 * @param firstName the first name of the user
 * @param lastName the last name of the user
 * @param email the email of the user
 */
public record AppUserDTO(
        String firstName,
        String lastName,
        String email
) {}

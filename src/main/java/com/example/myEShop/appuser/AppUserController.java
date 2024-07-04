package com.example.myEShop.appuser;

import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing application users.
 */
@RestController
@RequestMapping(path="/api/v1/user")
public class AppUserController {

    private final AppUserService appUserService;

    /**
     * Constructor for AppUserController.
     *
     * @param appUserService the service to handle user-related operations
     */
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    /**
     * Retrieves the information of the current user.
     *
     * @return an {@link AppUserDTO} containing user information
     */
    @GetMapping
    public AppUserDTO getUserInformation() {
        return appUserService.getAppUserInformation();
    }

    /**
     * Deletes the current user.
     */
    @DeleteMapping
    public void deleteAppUser() {
        appUserService.deleteAppUser();
    }

    /**
     * Updates the information of the current user.
     *
     * @param appUserRequest the request containing updated user information
     */
    @PutMapping
    public void updateAppUser(@RequestBody AppUserRequest appUserRequest) {
        appUserService.updateAppUser(appUserRequest);
    }
}

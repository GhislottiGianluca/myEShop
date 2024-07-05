package com.example.myEShop.registration;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;

/**
 * Controller for handling user registration and token confirmation.
 */
@RestController
@RequestMapping(path="api/v1/register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    /**
     * Registers a new user with the given registration request.
     * <p>
     * This endpoint handles user registration by delegating the request to
     * the {@link RegistrationService}. Depending on the result, it redirects
     * the user to the appropriate page.
     * </p>
     *
     * @param request the registration request containing user details
     * @return a {@link ResponseEntity} containing the redirect URL
     * @throws IOException if an input or output exception occurs
     */
    @PostMapping("/user")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) throws IOException {
        int result = registrationService.register(request);
        String redirectUrl = (result == 1) ? "/attending-confirmation" : "/home";
        return ResponseEntity.ok(Collections.singletonMap("redirectUrl", redirectUrl));
    }

    /**
     * Confirms the registration token.
     * <p>
     * This endpoint validates the provided token and confirms the user's registration.
     * </p>
     *
     * @param token the registration token
     * @return a confirmation message
     */
    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}

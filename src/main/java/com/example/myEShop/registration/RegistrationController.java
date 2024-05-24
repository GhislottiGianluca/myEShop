package com.example.myEShop.registration;

import com.example.myEShop.appuser.AppUserRepository;
import com.example.myEShop.security.PasswordEncoder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;


@RestController
@RequestMapping(path="api/v1/register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/user")
    public ResponseEntity<?> register(HttpServletResponse response, @RequestBody RegistrationRequest request) throws IOException {
        int result = registrationService.register(request);
        String redirectUrl = (result == 1) ? "/attending-confirmation" : "/home";//TODO review /cart endpoint
        return ResponseEntity.ok(Collections.singletonMap("redirectUrl", redirectUrl));
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

}

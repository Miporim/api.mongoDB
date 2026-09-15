package com.Fiap.fase5.api.mongoDB.auth;

import com.Fiap.fase5.api.mongoDB.auth.dto.RegisterRequest;
import com.Fiap.fase5.api.mongoDB.auth.dto.LoginRequest;
import com.Fiap.fase5.api.mongoDB.auth.dto.LoginResponse;
import com.Fiap.fase5.api.mongoDB.user.UserService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request) {
        var user = userService.register(request);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).build();
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> me(Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("email", authentication.getName()));
    }
}

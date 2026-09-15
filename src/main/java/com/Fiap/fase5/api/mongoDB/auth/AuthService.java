package com.Fiap.fase5.api.mongoDB.auth;

import com.Fiap.fase5.api.mongoDB.auth.dto.LoginRequest;
import com.Fiap.fase5.api.mongoDB.auth.dto.LoginResponse;
import com.Fiap.fase5.api.mongoDB.audit.AuditLogService;
import com.Fiap.fase5.api.mongoDB.security.CustomUserDetailsService;
import com.Fiap.fase5.api.mongoDB.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final AuditLogService auditLogService;

    public AuthService(AuthenticationManager authenticationManager,
                       CustomUserDetailsService userDetailsService,
                       JwtService jwtService, AuditLogService auditLogService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.auditLogService = auditLogService;
    }

    public LoginResponse login(LoginRequest request) {
        String email = request.email().trim().toLowerCase();

        try {
            authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken.unauthenticated(email, request.password())
            );
        } catch (BadCredentialsException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha inválidos");
        }

        var userDetails = userDetailsService.loadUserByUsername(email);
        auditLogService.register("LOGIN", "USER", null, email);
        return new LoginResponse(jwtService.generateToken(userDetails));
    }
}

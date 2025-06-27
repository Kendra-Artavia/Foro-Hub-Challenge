package org.example.forohubbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.forohubbackend.domain.user.AuthenticationResponse;
import org.example.forohubbackend.domain.user.LoginRequest;
import org.example.forohubbackend.domain.user.RegisterRequest;
import org.example.forohubbackend.domain.user.User;
import org.example.forohubbackend.infra.util.JwtUtil;
import org.example.forohubbackend.service.UserDetailsServiceImpl;
import org.example.forohubbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
        try {
            userService.registerUser(registerRequest);
            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            // Authenticate user using email and password
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.email(),
                            loginRequest.password()
                    )
            );
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        // Load user details for JWT generation
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.email());

        // Generate JWT token
        String token = jwtUtil.generateToken(userDetails);

        // Load actual user entity to fetch email and role
        User user = service.findUserByEmail(loginRequest.email());

        // Build the authentication response
        AuthenticationResponse response = new AuthenticationResponse(
                token,
                user.getEmail(),
                user.getProfile().getName()
        );

        // Return the response object as JSON
        return ResponseEntity.ok(response);
    }




}

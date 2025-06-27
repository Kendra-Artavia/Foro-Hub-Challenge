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
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
        // Si ocurre error de validación o negocio, será atrapado por GlobalExceptionHandler
        service.registerUser(registerRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.email());
        String token = jwtUtil.generateToken(userDetails);
        User user = service.findUserByEmail(loginRequest.email());

        AuthenticationResponse response = new AuthenticationResponse(
                token,
                user.getEmail(),
                user.getProfile().getName()
        );

        return ResponseEntity.ok(response);
    }
}

package org.example.forohubbackend.domain.user;

public record AuthenticationResponse(
        String token,
        String email,
        String role
) {}

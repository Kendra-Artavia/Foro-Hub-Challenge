package org.example.forohubbackend.service;


import lombok.RequiredArgsConstructor;
import org.example.forohubbackend.domain.ValidationException;
import org.example.forohubbackend.domain.repository.ProfileRepository;
import org.example.forohubbackend.domain.user.RegisterRequest;
import org.example.forohubbackend.domain.user.User;
import org.example.forohubbackend.domain.user.Profile;
import org.example.forohubbackend.domain.repository.UserRepository;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;


    public void registerUser(RegisterRequest request) {
        if (!request.password().equals(request.confirmPassword())) {
            throw new ValidationException("Passwords do not match");
        }

        if (userRepository.existsUserByEmail(request.email())) {
            throw new ValidationException("User already exists");
        }

        Profile profile = profileRepository.findByName(request.profile());
        if (profile == null) {
            throw new ValidationException("Profile not found");
        }

        User newUser = new User();
        newUser.setName(request.name());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setProfile(profile);
        userRepository.save(newUser);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}

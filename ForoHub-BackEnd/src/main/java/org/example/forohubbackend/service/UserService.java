package org.example.forohubbackend.service;


import lombok.RequiredArgsConstructor;
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
            throw new RuntimeException("Passwords don't match");
        }


        if (userRepository.existsUserByEmail(request.email()))
        {
            throw new RuntimeException("User already exists");
        }

       Profile profile = profileRepository.findByName(request.profile());

      if (profile != null) {
          User newUser = new User();
          newUser.setName(request.name());
          newUser.setEmail(request.email());
          newUser.setPassword(passwordEncoder.encode(request.password()));
          newUser.setProfile(profile);
          userRepository.save(newUser);
      }
      else
      {
          throw new RuntimeException("Profile not found");
      }

    }


    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


}

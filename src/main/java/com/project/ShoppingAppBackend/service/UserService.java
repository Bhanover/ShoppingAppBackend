package com.project.ShoppingAppBackend.service;

import com.project.ShoppingAppBackend.models.Role;
import com.project.ShoppingAppBackend.models.RoleName;
import com.project.ShoppingAppBackend.models.User;
import com.project.ShoppingAppBackend.payload.request.SignupRequest;
import com.project.ShoppingAppBackend.repositories.RoleRepository;
import com.project.ShoppingAppBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

  @Autowired private UserRepository userRepository;

  @Autowired private RoleRepository roleRepository;

  @Autowired private PasswordEncoder encoder;

  // Registrar un nuevo usuario
  public User registerUser(SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      throw new RuntimeException("Error: Username is already taken!");
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      throw new RuntimeException("Error: Email is already in use!");
    }

    // Crear una nueva instancia de usuario
    User user = new User();
    user.setUsername(signUpRequest.getUsername());
    user.setEmail(signUpRequest.getEmail());
    user.setPassword(encoder.encode(signUpRequest.getPassword()));

    // Asignar rol de usuario
    Role userRole =
        roleRepository
            .findByName(RoleName.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    user.setRoles(Collections.singleton(userRole));

    return userRepository.save(user);
  }
}

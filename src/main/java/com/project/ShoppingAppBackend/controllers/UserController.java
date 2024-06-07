package com.project.ShoppingAppBackend.controllers;

import com.project.ShoppingAppBackend.models.User;
import com.project.ShoppingAppBackend.payload.request.SignupRequest;
import com.project.ShoppingAppBackend.repositories.UserRepository;
import com.project.ShoppingAppBackend.payload.request.LoginRequest;
import com.project.ShoppingAppBackend.payload.response.JwtResponse;
import com.project.ShoppingAppBackend.security.jwt.AuthTokenFilter;
import com.project.ShoppingAppBackend.security.jwt.JwtUtils;
import com.project.ShoppingAppBackend.security.service.TokenBlacklistService;
import com.project.ShoppingAppBackend.security.service.UserDetailsImpl;
import com.project.ShoppingAppBackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {
  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private UserRepository userRepository;
  @Autowired private PasswordEncoder encoder;
  @Autowired private JwtUtils jwtUtils;
  @Autowired private TokenBlacklistService tokenBlacklistService;
  @Autowired private AuthTokenFilter authTokenFilter;
  @Autowired private UserService userService;

  // Autenticar usuario y crear sesión
  @PostMapping("/session")
  public ResponseEntity<?> authenticateUserSession(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles =
        userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
    String jwtToken = jwtUtils.generateTokenFromUsername(userDetails.getUsername());

    return ResponseEntity.ok(
        new JwtResponse(userDetails.getId(), userDetails.getUsername(), jwtToken, roles));
  }

  /*Este método maneja las solicitudes de cierre de sesión de los usuarios.*/
  @DeleteMapping("/session")
  public ResponseEntity<?> logoutUser(HttpServletRequest request) {
    String jwt = authTokenFilter.parseJwt(request);
    if (jwt != null) {
      tokenBlacklistService.blacklistToken(jwt);
      return ResponseEntity.ok("Log out successful!");
    } else {
      throw new RuntimeException("No se pudo obtener el token del usuario autenticado");
    }
  }

  // Registrar un nuevo usuario
  @Transactional
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
    try {
      User user = userService.registerUser(signUpRequest);
      return ResponseEntity.ok("User registered successfully!");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
  }
}

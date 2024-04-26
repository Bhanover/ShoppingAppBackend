package com.project.ShoppingAppBackend.controllers;

import com.project.ShoppingAppBackend.repositories.UserRepository;
import com.project.ShoppingAppBackend.payload.request.LoginRequest;
import com.project.ShoppingAppBackend.payload.response.JwtResponse;
import com.project.ShoppingAppBackend.security.jwt.AuthTokenFilter;
import com.project.ShoppingAppBackend.security.jwt.JwtUtils;
import com.project.ShoppingAppBackend.security.service.TokenBlacklistService;
import com.project.ShoppingAppBackend.security.service.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;

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
@RequestMapping("/api/auth")
public class UserController {
  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private UserRepository userRepository;
  @Autowired private PasswordEncoder encoder;
  @Autowired private JwtUtils jwtUtils;
  @Autowired private TokenBlacklistService tokenBlacklistService;
  @Autowired private AuthTokenFilter authTokenFilter;

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
  @DeleteMapping("/signout")
  public ResponseEntity<?> logoutUser(HttpServletRequest request) {
    String jwt = authTokenFilter.parseJwt(request);
    if (jwt != null) {
      tokenBlacklistService.blacklistToken(jwt);
      return ResponseEntity.ok("Log out successful!");
    } else {
      throw new RuntimeException("No se pudo obtener el token del usuario autenticado");
    }
  }
}

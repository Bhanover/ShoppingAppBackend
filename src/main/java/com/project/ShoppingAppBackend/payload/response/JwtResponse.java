package com.project.ShoppingAppBackend.payload.response;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private Long id;
    private String username;
    private String email;
    private String jwtToken;
    private List<String> roles;

    public JwtResponse(Long id, String username, String jwtToken, List<String> roles) {
        this.id = id;
        this.username = username;
        this.jwtToken = jwtToken;
        this.roles = roles;
    }
}
/*@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String jwt;
    private String type = "Bearer";
    private String username;
    private List<String> roles;

 @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Extrae el nombre de usuario autenticado del objeto Authentication
        String authenticatedUsername = authentication.getName();

        String jwt = jwtUtils.generateTokenFromUsername(authenticatedUsername);


        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Convertir la colección de GrantedAuthority a una lista de strings con los nombres de los roles
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Aquí puedes enviar más datos en la respuesta si lo deseas, como detalles del usuario
        return ResponseEntity.ok(new JwtResponse(jwt, "Bearer", userDetails.getUsername(), roles));
    }
}*/
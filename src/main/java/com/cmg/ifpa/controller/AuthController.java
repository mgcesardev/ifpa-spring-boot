package com.cmg.ifpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cmg.ifpa.model.Usuario;
import com.cmg.ifpa.dto.AuthRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final RestTemplate restTemplate;
    private static final String USERS_URL = "http://localhost:8081/users";

    public AuthController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            // Call the Users Microservice
            // The response is a Page<Usuario>, so we extract the "content" list
            Map<String, Object> response = restTemplate.getForObject(USERS_URL, Map.class);
            
            if (response == null || !response.containsKey("content")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al conectar con el servicio de usuarios.");
            }

            List<Map<String, Object>> usersList = (List<Map<String, Object>>) response.get("content");

            // Search for the user by email and verify password
            Optional<Map<String, Object>> userMatch = usersList.stream()
                .filter(u -> authRequest.getCorreoElectronico().equals(u.get("correoElectronico"))
                          && BCrypt.checkpw(authRequest.getContrasena(), (String) u.get("contrasena"))
                          && "A".equals(u.get("estatus")))
                .findFirst();

            if (userMatch.isPresent()) {
                return ResponseEntity.ok(userMatch.get());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas o usuario inactivo.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el microservicio de autenticación: " + e.getMessage());
        }
    }
}

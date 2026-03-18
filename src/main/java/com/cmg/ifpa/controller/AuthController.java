package com.cmg.ifpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cmg.ifpa.model.Usuario;
import com.cmg.ifpa.repository.UsuarioRepository;
import com.cmg.ifpa.dto.AuthRequest;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        // Find existing users with the email
        // Note: For a real app we'd use Spring Security + Password Encoding
        // The current table has the password in plain text.
        Optional<Usuario> usuarioOpt = usuarioRepository.findAll().stream()
            .filter(u -> authRequest.getCorreoElectronico().equals(u.getCorreoElectronico()) 
                      && authRequest.getContrasena().equals(u.getContrasena())
                      && "A".equals(u.getEstatus()))
            .findFirst();

        if (usuarioOpt.isPresent()) {
            return ResponseEntity.ok(usuarioOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas o usuario inactivo.");
        }
    }
}

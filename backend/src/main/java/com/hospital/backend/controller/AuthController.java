package com.hospital.backend.controller;

import com.hospital.backend.model.LoginRequest;
import com.hospital.backend.model.Usuario;
import com.hospital.backend.repository.UsuarioRepository;
import com.hospital.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@ RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

     @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
                .orElse(null);

        if (usuario == null || !passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
            return ResponseEntity.status(401).body("Email o contraseña incorrectos");
        }

        String token = jwtUtil.generarToken(usuario.getEmail(), usuario.getRol().name());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "email", usuario.getEmail(),
                "rol", usuario.getRol()
        ));
}
}
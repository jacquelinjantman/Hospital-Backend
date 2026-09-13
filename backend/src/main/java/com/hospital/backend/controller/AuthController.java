package com.hospital.backend.controller;

import com.hospital.backend.dto.RegistroPacienteRequest;
import com.hospital.backend.model.LoginRequest;
import com.hospital.backend.model.Rol;
import com.hospital.backend.model.Usuario;
import com.hospital.backend.repository.PacienteRepository;
import com.hospital.backend.repository.UsuarioRepository;
import com.hospital.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.hospital.backend.model.Paciente;


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

    @Autowired
private PacienteRepository pacienteRepository;


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

@PostMapping("/registro-paciente")
public ResponseEntity<?> registrarPaciente(@RequestBody RegistroPacienteRequest request) {

    if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
        return ResponseEntity.badRequest().body("Ese email ya está registrado");
    }
  Usuario usuario = new Usuario();
    usuario.setEmail(request.getEmail());
    usuario.setPassword(passwordEncoder.encode(request.getPassword()));
    usuario.setRol(Rol.PACIENTE);
    usuario = usuarioRepository.save(usuario);

Paciente paciente = new Paciente();
    paciente.setUsuario(usuario);
    paciente.setNombreCompleto(request.getNombreCompleto());
    paciente.setFechaNacimiento(request.getFechaNacimiento());
    paciente.setTelefono(request.getTelefono());
    paciente.setDni(request.getDni());
    pacienteRepository.save(paciente);

    String token = jwtUtil.generarToken(usuario.getEmail(), usuario.getRol().name());

    return ResponseEntity.ok(Map.of(
            "token", token,
            "email", usuario.getEmail(),
            "rol", usuario.getRol()
    ));
}
}
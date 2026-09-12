package com.hospital.backend.controller;

import com.hospital.backend.model.Turno;
import com.hospital.backend.repository.DoctorRepository;
import com.hospital.backend.repository.PacienteRepository;
import com.hospital.backend.repository.UsuarioRepository;
import com.hospital.backend.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/turnos")

public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired 
    private DoctorRepository doctorRepository;

    @Autowired 
    private PacienteRepository pacienteRepository;

    @GetMapping
    public List<Turno> listarTodos() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        var usuario = usuarioRepository.findByEmail(email).orElseThrow();

        return switch(usuario.getRol()){
            case DIRECTOR, ADMIN -> turnoService.listarTodos();

            case DOCTOR -> {
                var doctor = doctorRepository.findAll().stream()
                .filter(d -> d.getUsuario().getId().equals(usuario.getId()))
                .findFirst()
                .orElseThrow();
                yield turnoService.listarTodos().stream()
                .filter(t -> t.getDoctor().getId().equals(doctor.getId()))
                .collect(Collectors.toList());
            }

            case PACIENTE -> {
                var paciente = pacienteRepository.findAll().stream()
                .filter(p -> p.getUsuario().getId().equals(usuario.getId()))
                .findFirst()
                .orElseThrow();
                yield turnoService.listarTodos().stream()
                .filter(t -> t.getPaciente().getId().equals(paciente.getId()))
                .collect(Collectors.toList());
            }
            default -> List.of();
        };
    }
       
    

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarPorId(@PathVariable Long id) {
        return turnoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Turno crear(@RequestBody Turno turno) {
        return turnoService.guardar(turno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        turnoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

package com.hospital.backend.controller;

import com.hospital.backend.model.DisponibilidadDoctor;
import com.hospital.backend.service.DisponibilidadDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disponibilidad")
public class DisponibilidadDoctorController {

    @Autowired
    private DisponibilidadDoctorService disponibilidadService;

    @GetMapping
    public List<DisponibilidadDoctor> listarTodos() {
        return disponibilidadService.listarTodos();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DIRECTOR', 'DOCTOR')")
    @PostMapping
    public DisponibilidadDoctor crear(@RequestBody DisponibilidadDoctor disponibilidad) {
        return disponibilidadService.guardar(disponibilidad);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DIRECTOR', 'DOCTOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        disponibilidadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
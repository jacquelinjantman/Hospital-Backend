package com.hospital.backend.controller;

import com.hospital.backend.model.Doctor;
import com.hospital.backend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> listarTodos() {
        return doctorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> buscarPorId(@PathVariable Long id) {
        return doctorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Doctor crear(@RequestBody Doctor doctor) {
        return doctorService.guardar(doctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        doctorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/baja")
    public ResponseEntity<String> darDeBaja(@PathVariable Long id) {
        try {
            doctorService.darDeBaja(id);
            return ResponseEntity.ok("Doctor dado de baja correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

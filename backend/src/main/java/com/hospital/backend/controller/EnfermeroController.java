package com.hospital.backend.controller;

import com.hospital.backend.model.Enfermero;
import com.hospital.backend.service.EnfermeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enfermeros")
public class EnfermeroController {

    @Autowired
    private EnfermeroService enfermeroService;

    @GetMapping
    public List<Enfermero> listarTodos() {
        return enfermeroService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enfermero> buscarPorId(@PathVariable Long id) {
        return enfermeroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Enfermero crear(@RequestBody Enfermero enfermero) {
        return enfermeroService.guardar(enfermero);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        enfermeroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/baja")
    public ResponseEntity<String> darDeBaja(@PathVariable Long id) {
        try {
            enfermeroService.darDeBaja(id);
            return ResponseEntity.ok("Enfermero dado de baja correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
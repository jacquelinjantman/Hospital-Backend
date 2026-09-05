package com.hospital.backend.controller;

import com.hospital.backend.model.Administrador;
import com.hospital.backend.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping
    public List<Administrador> listarTodos() {
        return administradorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> buscarPorId(@PathVariable Long id) {
        return administradorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Administrador crear(@RequestBody Administrador administrador) {
        return administradorService.guardar(administrador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        administradorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/baja")
    public ResponseEntity<String> darDeBaja(@PathVariable Long id) {
        try {
            administradorService.darDeBaja(id);
            return ResponseEntity.ok("Administrador dado de baja correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
package com.hospital.backend.controller;

import com.hospital.backend.model.AdministradorSector;
import com.hospital.backend.service.AdministradorSectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/administradores/{administradorId}/sectores")
public class AdministradorSectorController {

    @Autowired
    private AdministradorSectorService administradorSectorService;

    @PostMapping("/{especialidadId}")
    public ResponseEntity<AdministradorSector> asignar(@PathVariable Long administradorId,
            @PathVariable Long especialidadId) {
        return ResponseEntity.ok(administradorSectorService.asignar(administradorId, especialidadId));
    }
}
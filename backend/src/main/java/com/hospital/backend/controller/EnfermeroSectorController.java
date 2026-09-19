package com.hospital.backend.controller;

import com.hospital.backend.model.EnfermeroSector;
import com.hospital.backend.service.EnfermeroSectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enfermeros/{enfermeroId}/sectores")
public class EnfermeroSectorController {

    private final EnfermeroSectorService enfermeroSectorService;

    public EnfermeroSectorController(EnfermeroSectorService enfermeroSectorService) {
        this.enfermeroSectorService = enfermeroSectorService;
    }

    @PostMapping("/{especialidadId}")
    public ResponseEntity<EnfermeroSector> asignar(@PathVariable Long enfermeroId, @PathVariable Long especialidadId) {
        return ResponseEntity.ok(enfermeroSectorService.asignar(enfermeroId, especialidadId));
    }
}